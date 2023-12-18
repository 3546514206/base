var Cat = function () {

}

var cat = new Cat();

console.log(Object.getPrototypeOf(cat) === Cat.prototype);

console.log(Object.getPrototypeOf(Object.prototype) === null);

function f() {
}

console.log(Object.getPrototypeOf(f) === Function.prototype);

console.log(Object.getPrototypeOf(cat) === Function.prototype);