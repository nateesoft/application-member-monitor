/* Redeem.model code generator by automatic script */

const config = require('../config')
const pool = require("../mysql-connect")(config.database)
const moment = require('moment')
const util = require('../utils/TextUtil');
const logger = require('../logger');

module.exports = () => {
  const module = {}
  const table_name = "redeem";

  module.findById = (id) => {
    logger.info(`findById: ${id}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `select * from ${table_name} where uuid_index=?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [id])
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.syncData = () => {
    logger.info(`syncData`)
    return new Promise(async (resolve, reject) => {
      try {
        const fieldCheck = 'redeem_code,bill_no';
        const sql = `SELECT ${fieldCheck} FROM 
        (SELECT ${fieldCheck} FROM ${table_name} t1 
        UNION ALL SELECT ${fieldCheck} FROM ${table_name}_temp) tbl
        GROUP BY ${fieldCheck} HAVING count(*) = 1 
        ORDER BY redeem_code;`;
        logger.debug(sql);
        const result = await pool.query(sql)
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.findByRedeemCode = (redeemCode) => {
    logger.info(`findByRedeemCode: ${redeemCode}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `select * from ${table_name} where redeem_code=?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [redeemCode])
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.findAll = () => {
    logger.info('findAll');
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `select * from ${table_name};`;
        logger.debug(sql);
        const result = await pool.query(sql)
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.syncData = () => {
    logger.info('syncData');
    return new Promise(async (resolve, reject) => {
      try {
        const fieldCheck = 'redeem_code, bill_no';
        const sql = `SELECT ${fieldCheck} FROM 
        (SELECT ${fieldCheck} FROM ${table_name} t1 
        UNION ALL SELECT ${fieldCheck} FROM ${table_name}_temp) tbl
        GROUP BY ${fieldCheck} HAVING count(*) = 1 
        ORDER BY bill_no;`;
        logger.debug(sql);
        const result = await pool.query(sql)
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.searchData = (key, value) => {
    logger.info(`searchData: ${key} ${value}`);
    return new Promise(async (resolve, reject) => {
      try {
        let sql = `select * from ${table_name}`
        if (key !== "") {
          sql = `${sql} where ${key} like '%${value}%'`;
        }
        logger.debug(sql);
        const result = await pool.query(sql)
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.bulkInsert = (objectArray) => {
    logger.info(`bulkInsert: ${objectArray}`)
    return new Promise(async (resolve, reject) => {
      try {
        let keys = Object.keys(objectArray[0]);
        let values = objectArray.map( obj => keys.map( key => obj[key]));
        let sql = `INSERT INTO ${table_name} (${keys.join(',')}) VALUES ? 
        ON DUPLICATE KEY UPDATE uuid_index=uuid_index;`;
        logger.debug(sql);
        const response = await pool.query(sql, [values]);
        resolve(response);
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    });
  }
  module.bulkInsertTemp = (objectArray) => {
    logger.info(`bulkInsertTemp: ${objectArray}`)
    return new Promise(async (resolve, reject) => {
      try {
        let keys = Object.keys(objectArray[0]);
        let values = objectArray.map( obj => keys.map( key => obj[key]));
        let sql = `INSERT INTO ${table_name}_temp (${keys.join(',')}) VALUES ? 
        ON DUPLICATE KEY UPDATE uuid_index=uuid_index;`;
        logger.debug(sql);
        const response = await pool.query(sql, [values]);
        resolve(response);
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    });
  }

  module.getQuery = (data) => {
    logger.info(`getQuery: ${data}`)
    return new Promise(async (resolve, reject) => {
      return resolve({
        uuid_index: data.uuid_index,
        redeem_code: data.redeem_code,
        product_code: data.product_code,
        redeem_name: util.convUnicode2Ascii(data.redeem_name),
        point_to_redeem: data.point_to_redeem,
        use_in_branch: data.use_in_branch,
        emp_code_redeem: data.emp_code_redeem,
        member_code_use: data.member_code_use,
        qty_in_use: data.qty_in_use,
        system_create: data.system_create ? moment(data.system_create).format('YYYY-MM-DD HH:mm:ss'): null,
        redeem_date: data.redeem_date ? moment(data.redeem_date).format('YYYY-MM-DD HH:mm:ss'): null,
        in_time: data.in_time ? moment(data.in_time).format('YYYY-MM-DD HH:mm:ss'): null,
        status_use: data.status_use,
        active: data.active,
        redeem_or_free: data.redeem_or_free,
        discount_amt: data.discount_amt,
        discount_percent: data.discount_percent
      });
    })
  }

  module.create = data => {
    logger.info(`create: ${data}`)
    return new Promise(async (resolve, reject) => {
      const payload = await module.getQuery(data);
      try {
        if(config.apiServiceDB === data.database){
          const sql = `INSERT INTO ${table_name} SET ?;`;
          logger.debug(sql);
          const result = await pool.query(sql, payload);
          resolve({ status: "Success", data: JSON.stringify(result) })
        }else{
          resolve({ status: "Success", data: JSON.stringify([])})
        }
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }
  module.createTemp = redeemCode => {
    logger.info(`createTemp: ${redeemCode}`)
    return new Promise(async (resolve, reject) => {
      try {
          const sql = `INSERT INTO ${table_name}_temp select * from ${table_name} where redeem_code = ?;`;
          logger.debug(sql);
          const result = await pool.query(sql, redeemCode);
          resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.update = (data) => {
    logger.info(`update: ${data}`)
    return new Promise(async (resolve, reject) => {
      const payload = await module.getQuery(data);
      try {
        const sql = `UPDATE ${table_name} 
        SET product_code=?,
        redeem_name=?,
        point_to_redeem=?,
        use_in_branch=?,
        emp_code_redeem=?,
        member_code_use=?,
        qty_in_use=?,
        system_create=?,
        redeem_date=?,
        in_time=?,
        status_use=?,
        active=?,
        redeem_or_free=?,
        discount_amt=?,
        discount_percent=? 
        WHERE redeem_code=?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [
          payload.product_code,
          payload.redeem_name,
          payload.point_to_redeem,
          payload.use_in_branch,
          payload.emp_code_redeem,
          payload.member_code_use,
          payload.qty_in_use,
          payload.system_create,
          payload.redeem_date,
          payload.in_time,
          payload.status_use,
          payload.active,
          payload.redeem_or_free,
          payload.discount_amt,
          payload.discount_percent,
          payload.redeem_code
        ])
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.delete = (id) => {
    logger.info(`delete: ${id}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `DELETE FROM ${table_name} WHERE uuid_index = ?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [id])
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.deleteTemp = (redeemCode) => {
    logger.info(`deleteTemp: ${redeemCode}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `DELETE FROM ${table_name} 
        WHERE redeem_code = ?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [redeemCode])
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  return module
}
