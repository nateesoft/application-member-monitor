const chai = require('chai')
const chaiHttp = require('chai-http')

chai.should()
chai.use(chaiHttp)
chai.use(require('chai-json-schema'))

const server = require('../bin/www')

const mochaTestPath = '/'

describe(`TEST ${mochaTestPath}`, ()=>{
    it("test / shold be success", done=>{
        chai
        .request(server)
        .get(`${mochaTestPath}`)
        .end((err, res)=>{
            res.should.be.json
            res.should.have.status(200)
            done()
        })
    })
})
