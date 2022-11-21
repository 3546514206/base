let a = 2;
const b = 3;
var c;

function multiply(e, f) {
    var g = 2;
    return e * f * g * a;
}

c = multiply(2, 3);

console.log(c);

console.log(global);