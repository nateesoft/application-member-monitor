const config = require('../config')
const pool = require("../mysql-connect")(config.databaseMember)
const moment = require('moment')
const util = require('../utils/TextUtil');
const logger = require('../logger');

module.exports = () => {
  const module = {}
  const table_name = "memmaster"

  module.findAll = () => {
    logger.info('member:findAll');
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `select * from ${table_name} order by Member_Code;`;
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
    logger.info('member:syncData');
    return new Promise(async (resolve, reject) => {
      try {
        const fieldCheck = 'Member_Code, Member_TotalPurchase, Member_TotalScore';
        const sql = `SELECT ${fieldCheck} FROM 
        (SELECT ${fieldCheck} FROM ${table_name} t1 
        UNION ALL SELECT ${fieldCheck} FROM ${table_name}_temp) tbl
        GROUP BY ${fieldCheck} HAVING count(*) = 1 
        ORDER BY Member_Code;`;
        logger.debug(sql);
        const result = await pool.query(sql)
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.findByMemberCode = memberCode => {
    logger.info(`member:findByMemberCode: ${memberCode}`);
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `select * from ${table_name} where Member_Code = ?;`;
        logger.debug(sql);
        const result = await pool.query(sql, memberCode)
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.bulkInsert = (objectArray) => {
    logger.info(`member:bulkInsert: ${objectArray}`)
    return new Promise(async (resolve, reject) => {
      try {
        let keys = Object.keys(objectArray[0]);
        let values = objectArray.map( obj => keys.map( key => obj[key]));
        let sql = `INSERT INTO ${table_name} (${keys.join(',')}) VALUES ? 
        ON DUPLICATE KEY 
        UPDATE 
        Member_TotalPurchase=VALUES(Member_TotalPurchase), 
        Member_TotalScore=VALUES(Member_TotalScore)`;
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
    logger.info(`member:bulkInsertTemp: ${objectArray}`)
    return new Promise(async (resolve, reject) => {
      try {
        let keys = Object.keys(objectArray[0]);
        let values = objectArray.map( obj => keys.map( key => obj[key]));
        let sql = `INSERT INTO ${table_name}_temp (${keys.join(',')}) VALUES ? 
        ON DUPLICATE KEY 
        UPDATE 
        Member_TotalPurchase=VALUES(Member_TotalPurchase), 
        Member_TotalScore=VALUES(Member_TotalScore)`;
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
    logger.info(`member:getQuery: ${data}`)
    return new Promise(async (resolve, reject) => {
      return resolve({
        Member_Code: data.code,
        Member_NameThai: util.convUnicode2Ascii(data.first_name),
        Member_HomeTel: data.mobile,
        Member_Email: data.email,
        Member_Brithday: data.birthday ? moment(data.birthday).format('YYYY-MM-DD'): null,
        Member_ExpiredDate: data.expired_date ? moment(data.expired_date).format('YYYY-MM-DD'): null,
        Member_AppliedDate: moment().format('YYYY-MM-DD'),
        Member_LastDateService: moment().format('YYYY-MM-DD'),
        Employee_CreateDate: moment().format('YYYY-MM-DD'),
        Employee_ModifyDate: moment().format('YYYY-MM-DD'),
        Member_TotalPurchase: data.total_purchase,
        Member_Mobile: data.mobile,
        Member_PointExpiredDate: data.point_expired_date ? moment(data.point_expired_date).format('YYYY-MM-DD'): null,
        Member_TotalScore: data.total_score,
        Member_TitleNameThai: util.convUnicode2Ascii(data.prefix),
        Member_SurnameThai: util.convUnicode2Ascii(data.last_name),
        Member_Active: 'Y',
        System_Created: data.system_created ? moment(data.system_created).format('YYYY-MM-DD HH:mm:ss'): null,
        System_Updated: data.system_updated ? moment(data.system_updated).format('YYYY-MM-DD HH:mm:ss'): null
      });
    })
  }

  module.create = data => {
    logger.info(`member:create: ${data}`)
    return new Promise(async (resolve, reject) => {
      const payload = await module.getQuery(data);
      try {
        if(config.apiServiceDB === data.database){
          const sql = `INSERT INTO ${table_name} SET ? `;
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

  module.createTemp = memberCode => {
    logger.info(`member:createTemp: ${memberCode}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `INSERT INTO ${table_name}_temp select * from ${table_name} where Member_Code = ?;`;
        logger.debug(sql);
        const result = await pool.query(sql, memberCode);
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.update = data => {
    logger.info(`member:update: ${data}`)
    return new Promise(async (resolve, reject) => {
      const payload = await module.getQuery(data);
      try {
        if(config.apiServiceDB === data.database){
          const sql = `UPDATE ${table_name} 
          SET Member_NameThai=?,
          Member_HomeTel=?, 
          Member_Email=?, 
          Member_Brithday=?, 
          Member_ExpiredDate=?, 
          Member_AppliedDate=?, 
          Member_LastDateService=?, 
          Employee_CreateDate=?, 
          Employee_ModifyDate=?, 
          Member_TotalPurchase=?, 
          Member_PointExpiredDate=?, 
          Member_TotalScore=?, 
          Member_TitleNameThai=?, 
          Member_SurnameThai=?, 
          Member_Active=?, 
          System_Created=?, 
          System_Updated=? 
          WHERE Member_Code=?;`;
          logger.debug(sql);
          const result = await pool.query(sql, [
            payload.Member_NameThai,
            payload.Member_HomeTel, 
            payload.Member_Email, 
            payload.Member_Brithday, 
            payload.Member_ExpiredDate, 
            payload.Member_AppliedDate, 
            payload.Member_LastDateService, 
            payload.Employee_CreateDate, 
            payload.Employee_ModifyDate, 
            payload.Member_TotalPurchase, 
            payload.Member_PointExpiredDate, 
            payload.Member_TotalScore, 
            payload.Member_TitleNameThai, 
            payload.Member_SurnameThai, 
            payload.Member_Active, 
            payload.System_Created, 
            payload.System_Updated,
            payload.Member_Code
          ]);
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

  module.updateMemberPoint = data => {
    logger.info(`member:updateMemberPoint: ${data}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `UPDATE ${table_name} 
        SET Member_TotalScore=?, 
        Member_TotalPurchase=?, 
        System_Updated=now() 
        WHERE Member_Code=?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [
          data.Member_TotalScore,
          data.Member_TotalPurchase,
          data.Member_Code
        ]);
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  module.deleteTemp = memberCode => {
    logger.info(`member:deleteTemp: ${memberCode}`)
    return new Promise(async (resolve, reject) => {
      try {
        const sql = `DELETE FROM ${table_name}_temp WHERE Member_Code=?;`;
        logger.debug(sql);
        const result = await pool.query(sql, [ memberCode ]);
        resolve({ status: "Success", data: JSON.stringify(result) })
      } catch (err) {
        logger.error(err);
        reject({ status: 'Error', msg: err.message })
      }
    })
  }

  return module
}
