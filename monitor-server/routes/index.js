var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.send({ version: 'monitor-server v1.0' });
});

module.exports = router;
