var fruit = "banana";

switch (fruit) {
    case "apple" :
        console.log("11");
        fruit = "banana";
        break;
    case "banana" :
        console.log("22");
    // fruit = "pear";
    case "pear":
        console.log("33");

    default :
        console.log("44");

}

var a = 3;

// 需要注意的是，switch语句后面的表达式，与case语句后面的表
// 示式比较运行结果时，采用的是严格相等运算符（===），而不是相等运算符（==），这意味着比较时不会发生类型转换。
switch (a) {
    case 1 + 2:
        console.log(a);
        break;
    default:
        console.log("default")
}