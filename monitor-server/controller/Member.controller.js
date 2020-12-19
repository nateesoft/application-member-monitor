const logger = require("../logger")
const Task = require("../model/Member.model")()
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
        logger.error(`member:createOrUpdate=>${err}`)
        reject(err)
      }
    })
  }

  module.findByMemberCode = (member_code) => {
    return new Promise(async (resolve, reject) => {
      try {
        const response = await Task.findByMemberCode(member_code)
        resolve(response)
      } catch (err) {
        logger.error(`member:findByMemberCode=>${err}`)
        reject(err)
      }
    })
  }

  module.deleteCreateTemp = (member_code) => {
    return new Promise(async (resolve, reject) => {
      try {
        await Task.deleteTemp(member_code)
        await Task.createTemp(member_code)
        resolve(true)
      } catch (err) {
        logger.error(`member:deleteCreateTemp=>${err}`)
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
        logger.error(`member:syncData=>${err}`)
        reject(err)
      }
    })
  }

  module.createMember = (member) => {
    return new Promise(async (resolve, reject) => {
      try {
        const response = await Task.create(member)
        if (response) {
          const memberCode = member.code || member.Member_Code
          await Task.createTemp(memberCode)
        }
        resolve(response)
      } catch (err) {
        logger.error(`member:createMember=>${err}`)
        reject(err)
      }
    })
  }

  return module
}
