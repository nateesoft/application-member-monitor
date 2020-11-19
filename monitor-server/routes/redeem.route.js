const { Router } = require("express")
const { json, urlencoded } = require("body-parser")
const request = require('request');
const Task = require('../model/Redeem.model')
const Controller = require('../controller/Redeem.controller')

module.exports = args => {
  const { apiServiceRedeem, apiServiceDB, apiServiceAuth, logger } = args;
  const router = Router()

  router.use(json())
  router.use(urlencoded({ extended: false }))

  const module = {}

  module.GET_SERVER = (req, res) => {
    logger.info('redeem:GET_SERVER');
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
        logger.error(error);
      } else {
        const result = await Controller().createOrUpdate(response.body);
        res.json(result);
      }
    });
  }

  module.SYNC_UPLOAD = async (req, res) => {
    logger.info('redeem:SYNC_UPLOAD')
    try {
      const response = await Task().syncData();
      const data = JSON.parse(response.data);
      if (data.length > 0) {
        const getData = await Task().findByRedeemCode(data[0].redeem_code)
        const parseData = JSON.parse(getData.data);
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
            logger.error(error);
          } else {
            const newData = JSON.parse(getData.data);
            await Task().deleteTemp(newData[0].redeem_code);
            await Task().createTemp(newData[0].redeem_code);
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
    logger.info('redeem:POST');
    try {
      const response = await Task().create(req.body);
      if (response) {
        const redeemCode = req.body.redeem_code;
        await Task().createTemp(redeemCode);
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
