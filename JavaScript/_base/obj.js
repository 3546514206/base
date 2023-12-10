var obj_a = {
    foo: 'Hello', bar: 'World'
};

console.log(obj_a)
console.log(obj_a.toString())

var obj = {p: 1};
console.log(Object.keys(obj));// ["p"]

delete obj.p; // true
console.log(obj.p);// undefined
console.log(Object.keys(obj));// []

var obj_1 = {};
if ('toString' in obj_1) {
    console.log(obj_1.hasOwnProperty('toString')) // false
}

// 属性的遍历
var obj_2 = {a: 1, b: 2, c: 3};

for (var i in obj_2) {
    console.log('键名：', i);
    console.log('键值：', obj_2[i]);
}

// 例一
var obj_5 = {
    p1: 1,
    p2: 2,
};
with (obj_5) {
    p1 = 4;
    p2 = 5;
}
// 等同于
obj_5.p1 = 4;
obj_5.p2 = 5;