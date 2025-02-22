eval('var a = 1;');
console.log(a);


// eval没有自己的作用域，都在当前作用域内执行
// 但是，凡是使用别名执行eval，eval内部一律是全局作用域。
var b = 1;

function f() {
    var b = 2;
    var e = eval;
    e('console.log(a)');
}

f() // 1