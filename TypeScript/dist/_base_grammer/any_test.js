"use strict";
let value = "hello";
value = 42; // 允许
value = { key: "value" }; // 允许
// 可以调用任何方法，但不会有类型检查和代码提示
value.doSomething();
let flag = true;
if (flag) {
    let blockScoped = "I am block scoped";
    console.log(blockScoped); // 输出: "I am block scoped"
}
// console.log(blockScoped); // Error: blockScoped is not defined
const anotherBlockScoped = "I am also block scoped";
console.log(anotherBlockScoped); // 输出: "I am also block scoped"
//# sourceMappingURL=any_test.js.map