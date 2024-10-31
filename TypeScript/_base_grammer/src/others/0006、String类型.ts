let str1 = String("RUNOOB");
let str2 = new String("GOOGLE");
let str3: string;
// @ts-ignore
str3 = str1.concat(str2);
// RUNOOBGOOGLE
console.log("str1 + str2 : " + str3)