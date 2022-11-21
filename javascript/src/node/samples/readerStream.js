let fs = require("fs");

let data;

// 创建输入流
let readStream = fs.createReadStream("./input.txt");
// 设置编码
readStream.setEncoding("utf-8");

// 处理流事件 --> data, end, and error
readStream.on('data', function (chunk) {
    data += chunk;
});

// readStream.on('end',function(){
//     console.log(data);
// });

readStream.on('error', function (err) {
    console.log(err.stack);
});
setTimeout(function () {
    console.log(data);
}, 5000);

console.log("程序执行完毕");