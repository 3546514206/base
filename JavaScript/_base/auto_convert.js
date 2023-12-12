// JS中以下三种情况会发生自动类型转换

// 第一种情况，不同类型的数据互相运算。
console.log(123 + 'abc');

// 第二种情况，对非布尔值类型的数据求布尔值。
if ('abc') {
    console.log('hello')
}  // "hello"

//第三种情况，对非数值类型的值使用一元运算符（即+和-）。
console.log(+{foo: 'bar'});
console.log(-[1, 2, 3]);