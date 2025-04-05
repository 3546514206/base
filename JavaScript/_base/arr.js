// 作为一门动态类型语言，JS的数组很特殊，声明的时候不仅可以不指定大小，还可以动态添加元素，甚至还可以保存不同类型的元素
var arr = [];
arr = [
    {a: 1},
    [1, 2, 3],
    function () {
        return true;
    }
];

console.log(arr[0]);
console.log(arr[1]);
console.log(arr[2]);