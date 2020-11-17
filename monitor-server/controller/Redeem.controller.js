module.exports = () => {
    const Task = require('../model/Redeem.model')();
    const config = require('../config')
    const module = {}

    module.createOrUpdate = (data_in_file) => {
        return new Promise(async (resolve, reject) => {
            try {
                const get = JSON.parse(data_in_file)
                const query = [];
                for(let payload of get.data){
                    query.push(await Task.getQuery({ ...payload, database: config.apiServiceDB })); 
                }
                if(query.length>0){
                    const response = await Task.bulkInsert(query);
                    if (response) {
                        await Task.bulkInsertTemp(query);
                    }
                    resolve(response);
                }else{
                    resolve('not found data to create')
                }
            } catch (error) {
                reject(error);
            }
        })
    }

    return module;
}
