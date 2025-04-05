// var 声明的变量只有全局作用域和函数作用域
// 如果需要块级作用域，需要用let声明
function example() {

    var localVar = "I am a local variable";
    console.log(localVar); // 可以访问局部变量

    if (true) {
        var innerVar = "I am also visible throughout the function";
    }

    // 可以访问内部声明的变量
    console.log(innerVar);
}

example();

console.log(localVar); // 这里会抛出 ReferenceError，因为 localVar 只在 example 函数内可见
console.log(innerVar); // 这里不可以访问 innerVar，因为它是函数内部声明的，具有函数作用域
