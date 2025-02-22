console.log(0.1 + 0.2);
console.log(0.1 + 0.2 === 0.3);

console.log(0.3 / 0.1);
console.log(0.3 - 0.2);
console.log(0.2 - 0.1);
console.log((0.3 - 0.2) === (0.2 - 0.1));


console.log(-0 === +0);
console.log((-0).toString());
console.log(typeof (-0).toString());

// NaN 不是任何数值，甚至不是它本身
console.log(NaN === NaN);
// 数组indexOf的比较是严格比较
console.log([NaN].indexOf(NaN));
console.log([2, 3, 4].indexOf(3));
console.log([2, 3, 4].indexOf("3"));
// 访问下标为1的数组
console.log([4, 3, 2, 1][1]);
// 数组内部索引的比较是不严格的
console.log([4, 2]["0"]);

// 以下是一些与数值相关的全局方法
console.log(parseInt('123.8'));
// 如果parseInt的参数不是字符串，则会先转为字符串再转换。
// 对于那些会自动转为科学计数法的数字，parseInt会将科学计数法的表示方法视为字符串，因此导致一些奇怪的结果。
parseInt(0.0000008); // 8
// 等同于
parseInt('8e-7'); // 8
parseFloat(true);  // NaN

console.log(isNaN(NaN));
console.log(isNaN("123"));

isFinite(Infinity); // false
isFinite(-Infinity); // false
isFinite(NaN); // false
isFinite(undefined); // false
isFinite(null); // true
isFinite(-1); // true
console.log(isFinite("123"));
