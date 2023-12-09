// 如果 JavaScript 预期某个位置应该是布尔值，会将该位置上现有的值自动转为布尔值。转换规则是除了下面六个值被转为false，其他值都视为true。
// undefined
// null
// false
// 0
// NaN
// ""或''（空字符串）
if ('') {
    console.log('true');
}
// 没有任何输出

// 空数组和空对象会被转换为true,注意到这一点，少写bug
if ([]) {
    console.log('true');
}
// true

if ({}) {
    console.log('true');
}
// true