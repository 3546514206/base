// js 中没有方法重载，后面定义的方法，会覆盖前面定义的方法
function add(x, y) {
    return x - y;
}

function add(x, y, z) {
    return x + y + z;
}

console.log(add(2, 3));      // 输出 5
console.log(add(2, 3, 4));   // 输出 9
