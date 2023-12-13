function f() {
    console.log(111);
}

// 在 JavaScript 中，通过 new 关键字和直接调用函数之间存在重要的区别。这涉及到函数在被调用时如何处理 this 关键字和返回值。
var a = new f();
f();

