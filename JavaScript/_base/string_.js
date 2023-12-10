console.log((function () {
    /*
line 1
line 2
line 3
*/
}).toString().split('\n').slice(1, -1).join('\n'))


// 字符串可以被视为字符数组
var s = 'hello';
s[0] // "h"
s[1] // "e"
s[4] // "o"

// 直接对字符串使用方括号运算符
'hello'[1] // "e"