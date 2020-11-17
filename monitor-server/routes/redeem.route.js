const { Router } = require("express")
const { json, urlencoded } = require("body-parser")
const request = require('request');
const fs = require('fs');
const Task = require('../model/Redeem.model')
const Controller = require('../controller/Redeem.controller')

module.exports = args => {
  const { apiServiceRedeem, apiServiceDB, apiServiceAuth } = args;
  const router = Router()

  router.use(json())
  router.use(urlencoded({ extended: false }))

  const module = {}

  module.GET_SERVER = (req, res) => {
    console.log('GET_SERVER');
    const options = {
      'method': 'GET',
      'url': apiServiceRedeem,
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
        const getData = await Task().findByRedeemCode(data[0].redeem_code)
        const parseData = JSON.parse(getData.data);
        // 2. send data[0] to server
        const options = {
          'method': 'PUT',
          'url': apiServiceRedeem,
          'headers': {
            'database': apiServiceDB,
            'Authorization': apiServiceAuth,
            'Content-Type': 'application/json',
          },
          'body': JSON.stringify(parseData[0]),
        }
        request(options, async (error, response) => {
          if (error) {
            console.log(error);
          } else {
            const newData = JSON.parse(getData.data);
            //    3.1 delete data[0] from db_temp
            const res1 = await Task().deleteTemp(newData[0].redeem_code);
            console.log(res1);
            //    3.2 insert data[0] into db_temp
            const res2 = await Task().createTemp(newData[0].redeem_code);
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
        const redeemCode = req.body.redeem_code;
        await Task().createTemp(redeemCode);
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
  // redeem
  router.get('/server', module.GET_SERVER); // all redeem from server
  router.get('/', module.SYNC_UPLOAD); // get old redeem
  router.post('/', module.POST); // create new redeem

  return router;
}
