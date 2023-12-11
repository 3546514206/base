// 使用字符串作为键
const obj1 = {
    key1: 'value1',
    key2: 'value2'
};

// 使用符号作为键
const mySymbol = Symbol('mySymbol');
const obj2 = {
    [mySymbol]: 'value'
};

// 使用数字作为键
const obj3 = {
    1: 'one',
    2: 'two'
};

console.log(obj1.key1);  // 输出 'value1'
console.log(obj2[mySymbol]);  // 输出 'value'
console.log(obj3[1]);  // 输出 'one'
