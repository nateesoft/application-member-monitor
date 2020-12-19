const chai = require('chai')
const chaiHttp = require('chai-http')

chai.should()
chai.use(chaiHttp)
chai.use(require('chai-json-schema'))

const server = require('../bin/www')

const mochaTestPath = '/api/redeem'

describe(`TEST ${mochaTestPath}`, ()=>{
    it("get / should be success", done=>{
        chai
        .request(server)
        .get(`${mochaTestPath}`)
        .end((err, res)=>{
            res.should.be.json
            res.should.have.status(200)
            done()
        })
    })
    it.skip("get /server should be success", done=>{
        chai
        .request(server)
        .get(`${mochaTestPath}/server`)
        .end((err, res)=>{
            res.should.be.json
            res.should.have.status(200)
            done()
        })
    })
    it.skip("post / should be success", done=>{
        chai
        .request(server)
        .post(`${mochaTestPath}`)
        .send({
            "uuid_index": "1340-03040-30403-0346",
            "redeem_code": "",
            "product_code": "",
            "redeem_name": "xxxxx",
            "point_to_redeem": 5,
            "use_in_branch": "",
            "emp_code_redeem": "",
            "member_code_use": "",
            "qty_in_use": 50,
            "system_create": "1986-09-17T17:00:00.000Z",
            "redeem_date": "1986-09-17T17:00:00.000Z",
            "in_time": "1986-09-17T17:00:00.000Z",
            "status_use": "Y",
            "active": "Y",
            "redeem_or_free": "R",
            "discount_amt": 0,
            "discount_percent": 10,
            "database": "d2ViZGFpbHlfMDAx"
        })
        .end((err, res)=>{
            res.should.be.json
            res.should.have.status(200)
            done()
        })
    })
})
