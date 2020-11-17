const Promise = require('bluebird');
const mysql = require('mysql');

module.exports = db => {
    const pool = mysql.createPool(db);
    pool.query = Promise.promisify(pool.query);

    return pool;
}
