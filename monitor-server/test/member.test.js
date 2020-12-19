const chai = require('chai')
const chaiHttp = require('chai-http')

chai.should()
chai.use(chaiHttp)
chai.use(require('chai-json-schema'))

const server = require('../bin/www')

const mochaTestPath = '/api/member'

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
        .post(`${mochaTestPath}/server`)
        .send({
            "code": "M11116",
            "first_name": "",
            "mobile": "",
            "email": "",
            "birthday": "1986-09-17T17:00:00.000Z",
            "expired_date": "1986-09-17T17:00:00.000Z",
            "total_purchase": 0,
            "point_expired_date": "1986-09-17T17:00:00.000Z",
            "total_score": 0,
            "prefix": "",
            "last_name": "",
            "system_created": "1986-09-17T17:00:00.000Z",
            "system_updated": "1986-09-17T17:00:00.000Z",
            "database": "d2ViZGFpbHlfMDAx"
        })
        .end((err, res)=>{
            res.should.be.json
            res.should.have.status(200)
            done()
        })
    })
})
