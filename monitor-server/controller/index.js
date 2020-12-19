const { isDev } = require('../config')
const MemberController = isDev ? require('./test/Member.controller') : require('./Member.controller')
const RedeemController = isDev ? require('./test/Redeem.controller') : require('./Redeem.controller')

module.exports = {
    MemberController,
    RedeemController,
}
