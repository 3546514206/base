
const fs = require("fs");
    
fs.readFile('./1.txt', function (err, data) {
    if (err) {
        return console.error(err);
    }
    console.log(err);
    console.log("异步读取: " + data.toString());
});

// 同步读取
var data = fs.readFileSync('./1.txt');
console.log("同步读取: " + data.toString());

console.log("程序执行完毕。");




