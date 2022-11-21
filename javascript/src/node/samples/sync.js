let fs = require("fs");

let data = fs.readFileSync("./input.txt");

console.log(data.toString());
console.log("程序执行结束!");
