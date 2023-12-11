// 闭包的最大用处有两个:
// 一个是可以读取外层函数内部的变量;
// 另一个就是让这些变量始终保持在内存中，即闭包可以使得它诞生环境一直存在。
function createIncrementor(start) {
    return function () {
        return start++;
    };
}

var inc = createIncrementor(5);

console.log(inc());
console.log(inc());
console.log(inc());