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
