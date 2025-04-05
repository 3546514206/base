function Cat(name) {

}

// 1、构造函数通过prototype访问构造函数的原型对象
console.log(Cat.prototype);

//2、实例通过__proto__访问
var cat = new Cat("tim");
console.log(cat.__proto__);

var a = Object.getPrototypeOf(Cat);
var b = Object.getPrototypeOf(cat);