/**
 * @name：node.js操作mysql数据库模块
 * @author：SunSeekerX
 * @time：2019年6月24日22点26分
 * @dependencies: {
 *   "mysql": "^2.16.0"
 * }
 */

// 引入Mysql模块
const mysql = require('mysql')
// 根据信息创建连接池
const pool = mysql.createPool({
    host: 'localhost',          // 数据库地址
    port: 3306,                 // 数据库端口
    database: 'test',           // 数据库名
    user: 'root',               // 用户名
    password: 'root',           // 数据库密码
    acquireTimeout: 15000,      // 连接超时时间
    connectionLimit: 50,        // 最大连接数
    waitForConnections: true,   // 超过最大连接时排队
    queueLimit: 0,              // 排队最大数量(0 代表不做限制)
})

/**
 * @description 基本查询
 * @param {Object} sqlObj sql+参数对象
 * @returns {Object} 执行结果
 */
async function query(sqlObj) {
    return new Promise((resolve, reject) => {
        pool.getConnection((err, connection) => { // 从连接池获取连接
            if (err) {
                return reject(err)
            } // 获取连接失败，返回错误
            connection.query(sqlObj.sql, sqlObj.params, (error, result) => {
                connection.release() // 释放连接
                error ? reject(error) : resolve(result) // 查询结果
            })
        })
    })
}

/**
 * @description 开启事物查询
 * @param {Array} sqlArr sql+参数对象集合
 * @returns {Object} 执行结果
 */
async function transaction(sqlArr) {
    return new Promise((resolve, reject) => {
        pool.getConnection((poolError, connection) => { // 从连接池获取连接
            if (poolError) {
                return reject(poolError)
            } // 获取连接失败，返回错误
            connection.beginTransaction(async err => { // 开始事务
                if (err) {
                    return reject(err)
                } // 开始事务失败，返回错误
                let result = [],	// 结果集
                    errInfo = null	//错误对象
                for (let i = 0; i < sqlArr.length; i++) { // 循环查询
                    try {
                        result.push(await new Promise((resolve, reject) => { // 将查询结果放进结果集
                            connection.query(sqlArr[i].sql, sqlArr[i].params, (err, result) => { // 查询
                                err ? reject(err) : resolve(result)
                            })
                        }))
                    } catch (e) { // sql语句执行出错，跳出循环，不继续执行
                        errInfo = e
                        break
                    }
                }
                pool.releaseConnection(connection) // 释放链接
                if (errInfo) {
                    connection.rollback(() => { // 有数据条目执行失败, 回滚代码
                        reject(errInfo)
                    })
                } else {
                    connection.commit(err => { // 语句全部执行成功，commit提交
                        err ? reject(err) : resolve(result)
                    })
                }
            })
        })
    })
}

module.exports = {query, transaction}
