// JavaScript 是一种动态类型语言，也就是说，变量的类型没有限制，变量可以随时更改类型。
var a = 3;
a = "hello js"
console.log(a)

// 同一个 var 语句可以声明多个变量
// 这里第二次声明a是无效的
var a, b;
// 但是第二次声明b是有效的，相当于重新赋值
var b = 4
console.log(a + ":" + b);

// 访问未定义的变量e会报错
// console.log(e);

// 变量提升会导致，console#log 方法访问c也不会报错，因为第18行代码的声明会在构造抽象语法树期间提升至开头
console.log(c);
var c = 1;

// 但是这样是可以的，等价于 var f = 7
f = 7;
console.log(f);


// 以下不是变量提升，只是普通的变量未定义
var d;
console.log(d)
d = 9;

// 合法的变量名
var arg0 = 11, _tmp = "23", $elem = "qw", π = 'c';
// 不合法的变量名：
// 1a  // 第一个字符不能是数字
// 23  // 同上
// ***  // 标识符不能包含星号
// a+b  // 标识符不能包含加号
// -d  // 标识符不能包含减号或连词线
console.log(arg0 + " : " + _tmp + " : " + π)

// 中文也可以作为变量名
var 临时变量 = 1;
console.log(临时变量)

// 注释就不多说了，由于历史上 JavaScript 可以兼容 HTML 代码的注释，所以<!--和-->也被视为合法的单行注释。
var g = 78; <!-- h = 87;  -->

console.log(g);
// 以下代码会报错，因为 "--> var h = 87" 是注释
// console.log(h);

// 需要注意的是，-->只有在行首，才会被当成单行注释，否则会当作正常的运算。
function countdown(n) {
    while (n-- > 0) console.log(n);
}

countdown(3)

{
    var m = 18;
}
// 访问m不会报错，因为var声明的变量，是全局的。为了声明局部的变量，应当尽量使用let和const声明变量
console.log(m)


var o = 98;
var p = 99;
if (o = p) {
    console.log(o)
}

// var i = 0;

// while (i < 100) {
//     console.log('i 当前为：' + i);
//     i = i + 1;
// }

console.log(undefined == null)
console.log(undefined === null)

var q = Number(null);
console.log(q);


console.log(3 !== '3');// true，值相等但数据类型不同
console.log(3 !== 3);// false，值和数据类型都相同)

console.log(3 != '3');  // false，值相等，不关心数据类型
console.log(3 != 4); // true，值不相等
