const { Router } = require("express")
const { json, urlencoded } = require("body-parser")
const request = require('request');
const Task = require('../model/Member.model')
const Controller = require('../controller/Member.controller')

module.exports = args => {
  const { apiServiceMember, apiServiceDB, apiServiceAuth } = args;
  const router = Router()

  router.use(json())
  router.use(urlencoded({ extended: false }))

  const module = {}

  module.GET_SERVER = (req, res) => {
    console.log('GET_SERVER');
    const options = {
      'method': 'GET',
      'url': apiServiceMember,
      'headers': {
        'database': apiServiceDB,
        'Authorization': apiServiceAuth
      }
    };
    request(options, async (error, response) => {
      if (error) {
        console.log(error);
      } else {
        const result = await Controller().createOrUpdate(response.body);
        res.json(result);
      }
    });
  }

  module.SYNC_UPLOAD = async (req, res) => {
    try {
      const response = await Task().syncData();
      const data = JSON.parse(response.data);
      if(data.length>0){
        // 1. find data[0] from db
        const getData = await Task().findByMemberCode(data[0].Member_Code);
        // 2. send data[0] to server
        const parseData = JSON.parse(getData.data);
        const options = {
          'method': 'PUT',
          'url': apiServiceMember,
          'headers': {
            'database': apiServiceDB,
            'Authorization': apiServiceAuth,
            'Content-Type': 'application/json',
          },
          'body': JSON.stringify(parseData[0]),
        };
        request(options, async (error, response) => {
          if (error) {
            console.log(error);
          } else {
            const newData = JSON.parse(getData.data);
            //    3.1 delete data[0] from db_temp
            const res1 = await Task().deleteTemp(newData[0].Member_Code);
            console.log(res1);
            //    3.2 insert data[0] into db_temp
            const res2 = await Task().createTemp(newData[0].Member_Code);
            console.log(res2);
          }
        });
      }
      res.status(200).json({
        status: response.status,
        msg: 'Success',
        data
      })
    } catch (error) {
      return res
      .status(500)
      .json({ status: "Internal Server Error", msg: error.sqlMessage })
    }
  }

  module.POST = async (req, res) => {
    try {
      const response = await Task().create(req.body);
      if (response) {
        const memberCode = req.body.code || req.body.Member_Code;
        await Task().createTemp(memberCode);
      }
      const data = JSON.parse(response.data);
      res.status(200).json({ status: response.status, msg: "Success", data })
    } catch (error) {
      return res
      .status(500)
      .json({ status: "Internal Server Error", msg: error.sqlMessage })
    }
  }

  // local database
  // member
  router.get('/server', module.GET_SERVER); // get member from server
  router.get('/', module.SYNC_UPLOAD); // sync member to server
  router.post('/', module.POST); // create new member

  return router;
}
