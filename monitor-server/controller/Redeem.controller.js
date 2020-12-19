const logger = require("../logger")
const Task = require("../model/Redeem.model")()
const config = require("../config")

module.exports = () => {
  const module = {}

  module.createOrUpdate = (data_in_file) => {
    return new Promise(async (resolve, reject) => {
      try {
        const get = JSON.parse(data_in_file)
        const query = []
        for (let payload of get.data) {
          query.push(
            await Task.getQuery({ ...payload, database: config.apiServiceDB })
          )
        }
        if (query.length > 0) {
          const response = await Task.bulkInsert(query)
          if (response) {
            await Task.bulkInsertTemp(query)
          }
          return resolve(response)
        }
        return resolve("not found data to create")
      } catch (err) {
        logger.error(`redeem:createOrUpdate=>${err}`)
        reject(err)
      }
    })
  }

  module.findByRedeemCode = (redeem_code) => {
    return new Promise(async (resolve, reject) => {
      try {
        const response = await Task.findByRedeemCode(redeem_code)
        resolve(response)
      } catch (err) {
        logger.error(`redeem:findByRedeemCode=>${err}`)
        reject(err)
      }
    })
  }

  module.deleteCreateTemp = (redeem_code) => {
    return new Promise(async (resolve, reject) => {
      try {
        await Task.deleteTemp(redeem_code)
        await Task.createTemp(redeem_code)
        resolve(true)
      } catch (err) {
        logger.error(`redeem:deleteCreateTemp=>${err}`)
        reject(err)
      }
    })
  }

  module.syncData = () => {
    return new Promise(async (resolve, reject) => {
      try {
        const response = await Task.syncData()
        resolve(response)
      } catch (err) {
        logger.error(`redeem:syncData=>${err}`)
        reject(err)
      }
    })
  }

  module.createRedeem = (redeem) => {
    return new Promise(async (resolve, reject) => {
      try {
        const response = await Task.create(redeem)
        if (response) {
          const redeemCode = redeem.redeem_code
          await Task.createTemp(redeemCode)
        }
        resolve(response)
      } catch (err) {
        logger.error(`redeem:createRedeem=>${err}`)
        reject(err)
      }
    })
  }


  return module
}
