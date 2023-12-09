// 变量声明了，但没有赋值
var i;
// undefined
console.log(i)

// 调用函数时，应该提供的参数没有提供，该参数等于 undefined
function f(x) {
    return x;
}

// undefined
console.log(f());

// 对象没有赋值的属性
var o = new Object();
// undefined
console.log(o.p)

// 函数没有返回值时，默认返回 undefined
function f() {
}

// undefined
console.log(f())
