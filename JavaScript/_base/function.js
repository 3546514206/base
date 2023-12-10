function f() {
    let a = 1;
    let b = 2;
    return a + b;
}

console.log(f.toString())

// 第一种声明
function print(s) {
    console.log(s);
}

//第二种声明
var print_1 = function (s) {
    console.log(s);
};

// 第三中声明
var add = new Function(
    'x',
    'y',
    'return x + y'
);

// 等同于
// function add(x, y) {
//     return x + y;
// }


function f_1() {
    console.log(1);
}

f_1() // 2

function f_1() {
    console.log(2);
}

f_1() // 2