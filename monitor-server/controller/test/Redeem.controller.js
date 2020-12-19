module.exports = () => {
  const module = {}

  module.createOrUpdate = (data_in_file) => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  module.findByRedeemCode = (redeem_code) => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  module.deleteCreateTemp = (redeem_code) => {
    return new Promise(async (resolve, reject) => {
      return resolve(true)
    })
  }

  module.syncData = () => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  module.createRedeem = (redeem) => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }


  return module
}
