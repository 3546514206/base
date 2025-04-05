console.log('3' + 4 + 5);
console.log(3 + 4 + '5');
var obj = {p: '1'};
console.log(obj + 2);
console.log(obj.toString());
console.log(obj.valueOf());
console.log(obj.valueOf().toString());

var obj_1 = {
    p: '1',
    valueOf: function () {
        return 1;
    }
};

console.log(obj_1 + 2);