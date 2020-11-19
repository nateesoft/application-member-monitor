const { Router } = require("express")
const { json, urlencoded } = require("body-parser")
const request = require('request');
const Task = require('../model/Member.model')
const Controller = require('../controller/Member.controller')

module.exports = args => {
  const { apiServiceMember, apiServiceDB, apiServiceAuth, logger } = args;
  const router = Router()

  router.use(json())
  router.use(urlencoded({ extended: false }))

  const module = {}

  module.GET_SERVER = (req, res) => {
    logger.info('member:GET_SERVER');
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
        logger.error(error);
      } else {
        const result = await Controller().createOrUpdate(response.body);
        res.json(result);
      }
    });
  }

  module.SYNC_UPLOAD = async (req, res) => {
    logger.info('member:SYNC_UPLOAD');
    try {
      const response = await Task().syncData();
      const data = JSON.parse(response.data);
      if(data.length > 0){
        const getData = await Task().findByMemberCode(data[0].Member_Code);
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
            logger.error(error);
          } else {
            const newData = JSON.parse(getData.data);
            await Task().deleteTemp(newData[0].Member_Code);
            await Task().createTemp(newData[0].Member_Code);
          }
        });
      }
      res.status(200).json({
        status: response.status,
        msg: 'Success',
        data
      })
    } catch (err) {
      logger.error(err);
      return res
      .status(500)
      .json({ status: "Internal Server Error", msg: err.sqlMessage })
    }
  }

  module.POST = async (req, res) => {
    logger.info('member:POST');
    try {
      const response = await Task().create(req.body);
      if (response) {
        const memberCode = req.body.code || req.body.Member_Code;
        await Task().createTemp(memberCode);
      }
      const data = JSON.parse(response.data);
      res.status(200).json({ status: response.status, msg: "Success", data })
    } catch (err) {
      logger.error(err);
      return res
      .status(500)
      .json({ status: "Internal Server Error", msg: err.sqlMessage })
    }
  }

  // local database
  router.get('/server', module.GET_SERVER);
  router.get('/', module.SYNC_UPLOAD);
  router.post('/', module.POST);

  return router;
}
