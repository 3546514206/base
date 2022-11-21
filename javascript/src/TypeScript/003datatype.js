// any类型
// 声明为 any 的变量可以赋予任意类型的值。
var x = 1; // 数字类型
console.log(x);
x = 'I am who I am'; // 字符串类型
console.log(x);
x = false; // 布尔类型
console.log(x);
// 数值类型
var binaryLiteral = 10; // 二进制
console.log("二进制：" + binaryLiteral);
var octalLiteral = 484; // 八进制
console.log("八进制：" + octalLiteral);
var decLiteral = 6; // 十进制
console.log("十进制：" + decLiteral);
var hexLiteral = 0xf00d; // 十六进制
console.log("十六进制：" + hexLiteral);
// 字符串类型
var nama = "Runoob";
console.log(nama);
var years = 5;
console.log(years);
var words = "\u60A8\u597D\uFF0C\u4ECA\u5E74\u662F ".concat(nama, " \u53D1\u5E03 ").concat(years + 1, " \u5468\u5E74");
console.log(words);
// 布尔类型
var flag = true;
console.log(flag);
// 数组类型
// 在元素类型后面加上[]
var arr = [1, 2];
// 或者使用数组泛型
console.log(arr);
var arr1 = [1, 2];
console.log(arr1);
// 元组
// 元组类型用来表示已知元素数量和类型的数组，各元素的类型不必相同，对应位置的类型需要相同。
var tuple;
tuple = ['Runoob', 1]; // 运行正常
// tuple = [1, 'Runoob'];    // 报错
console.log(tuple[0]); // 输出 Runoob
console.log(tuple[1]);
tuple[1] = 10;
console.log(tuple[1]);
// 枚举类型用于定义数值集合
var Color;
(function (Color) {
    Color[Color["Red"] = 0] = "Red";
    Color[Color["Green"] = 1] = "Green";
    Color[Color["Blue"] = 2] = "Blue";
})(Color || (Color = {}));
var c = Color.Blue;
console.log("枚举值：" + c); // 输出 2
console.log(typeof c);
