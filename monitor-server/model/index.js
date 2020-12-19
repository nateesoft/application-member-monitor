const { isDev } = require('../config')
const MemberModel = isDev ? require('./test/Member.model'):require('./Member.model')
const RedeemModel = isDev ? require('./test/Redeem.model'):require('./Redeem.model')

module.exports = {
    MemberModel,
    RedeemModel
}