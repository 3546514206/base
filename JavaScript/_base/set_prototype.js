function Cat() {
    this.mew = function () {
        console.log("mew mew");
    }
}

function Animal(name) {
    this.name = name;
    this.jump = function () {
        console.log("jump");
    }
}

var cat = new Cat();
cat.mew();
var animal = new Animal();
Object.setPrototypeOf(cat,animal);
cat.jump();

console.log(cat.__proto__);
console.log(cat.prototype);
console.log(Cat.__proto__);
console.log(Cat.prototype);
