let obj = {key: "value"};
console.log(obj instanceof Object);  // 输出 true

let n = null;
console.log(n instanceof Object);   // 输出 false，因为 null 不是 Object 的实例
console.log(typeof n === "object");  // 输出 true