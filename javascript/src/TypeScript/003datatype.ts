// any类型
// 声明为 any 的变量可以赋予任意类型的值。
let x: any = 1;    // 数字类型
console.log(x);
x = 'I am who I am';    // 字符串类型
console.log(x);
x = false;    // 布尔类型
console.log(x);

// 数值类型
let binaryLiteral: number = 0b1010; // 二进制
console.log("二进制：" + binaryLiteral);
let octalLiteral: number = 0o744;    // 八进制
console.log("八进制：" + octalLiteral);
let decLiteral: number = 6;    // 十进制
console.log("十进制：" + decLiteral);
let hexLiteral: number = 0xf00d;    // 十六进制
console.log("十六进制：" + hexLiteral);

// 字符串类型
let nama: string = "Runoob";
console.log(nama);
let years: number = 5;
console.log(years);
let words: string = `您好，今年是 ${nama} 发布 ${years + 1} 周年`;
console.log(words);

// 布尔类型
let flag: boolean = true;
console.log(flag);

// 数组类型
// 在元素类型后面加上[]
let arr: number[] = [1, 2];
// 或者使用数组泛型
console.log(arr);
let arr1: Array<number> = [1, 2];
console.log(arr1);

// 元组
// 元组类型用来表示已知元素数量和类型的数组，各元素的类型不必相同，对应位置的类型需要相同。
let tuple: [string, number];
tuple = ['Runoob', 1];    // 运行正常
// tuple = [1, 'Runoob'];    // 报错
console.log(tuple[0]);    // 输出 Runoob
console.log(tuple[1]);
tuple[1] = 10;
console.log(tuple[1]);

// 枚举类型用于定义数值集合
enum Color {Red, Green, Blue}

let c: Color = Color.Blue;
console.log("枚举值：" + c);    // 输出 2
console.log(typeof c);

// 用于标识方法返回值的类型，表示该方法没有返回值。
function hello(): void {
    alert("Hello Runoob");
}

// null 表示对象值缺失。
// undefined 用于初始化变量为一个未定义的值
// never 是其它类型（包括 null 和 undefined）的子类型，代表从不会出现的值。