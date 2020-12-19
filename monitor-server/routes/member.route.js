const { Router } = require("express")
const { json, urlencoded } = require("body-parser")
const request = require('request');
const MemberController = require("../controller").MemberController

module.exports = args => {
  const { apiServiceMember, apiServiceDB, apiServiceAuth, logger } = args;
  const router = Router()

  router.use(json())
  router.use(urlencoded({ extended: false }))

  const module = {}

  module.GET_SERVER = (req, res) => {
    try {
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
          return res.status(500).json({ status: "Error", msg: error })
        } else {
          const result = await MemberController().createOrUpdate(response.body);
          return res.json(result);
        }
      });
    } catch (err) {
      logger.error(err.msg);
      return res.status(500).json({ status: err.status, msg: err.msg })
    }
  }

  module.SYNC_UPLOAD = async (req, res) => {
    try {
      const response = await MemberController().syncData();
      const data = JSON.parse(response.data);
      if(data.length > 0){
        const getData = await MemberController().findByMemberCode(data[0].Member_Code);
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
            await MemberController().deleteCreateTemp(newData[0].Member_Code)
          }
        });
      }
      res.status(200).json({ status: response.status, msg: 'Success', data })
    } catch (err) {
      logger.error(err.msg);
      return res.status(500).json({ status: err.status, msg: err.msg })
    }
  }

  module.POST = async (req, res) => {
    try {
      const response = await MemberController().createMember(req.body);
      const data = JSON.parse(response.data);
      res.status(200).json({ status: response.status, msg: "Success", data })
    } catch (err) {
      logger.error(err.msg);
      return res.status(500).json({ status: err.status, msg: err.msg })
    }
  }

  // local database
  router.get('/server', module.GET_SERVER);
  router.get('/', module.SYNC_UPLOAD);
  router.post('/', module.POST);

  return router;
}
