module.exports = () => {
  const module = {}

  module.createOrUpdate = (data_in_file) => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  module.findByMemberCode = (member_code) => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  module.deleteCreateTemp = (member_code) => {
    return new Promise(async (resolve, reject) => {
      return resolve(true)
    })
  }

  module.syncData = () => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  module.createMember = (member) => {
    return new Promise(async (resolve, reject) => {
      return resolve({})
    })
  }

  return module
}
