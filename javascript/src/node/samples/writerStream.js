let writerStream = require("fs").createWriteStream("./out.txt");

let data = "这是写入的数据";

writerStream.write(data, function () {
    console.log("写入完成。");
});

console.log("程序执行完毕");