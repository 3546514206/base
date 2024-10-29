// 二进制数
let bin_a: number = 0b0110;
console.log(bin_a);

// 八进制数值
let octal_b: number = 0o17;
console.log(octal_b);

// 十进制数值
let dec_c: number = 6;
console.log(dec_c);

// 字符串和字符串占位符
let str_name: string = "setsuna";
let year: number = 9;
let words: string = `this is ${str_name}'s ${year + 2} year` + "!";
console.log(words);

// 布尔类型
let bol: boolean = true;
console.log(bol);

// 基本类型的数组类型
let arr_a: number[] = [1, 2];
console.log(arr_a);

// 数组对象
let arr_b: Array<number> = new Array<number>();
arr_b.push(2, 3, 4);
arr_b.push(7);
console.log(arr_b);

// 元组
let tuple_a: [string, number, boolean] = ["aaaa", 1, true];
// 元组重新赋值
tuple_a = ["bbbb", 1, true];
console.log(tuple_a);
console.log(tuple_a[1]);
// 元组对应位置类型不一样会报错
// tuple_a = [2, 1, true];