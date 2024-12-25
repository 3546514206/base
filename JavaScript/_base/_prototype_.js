// JavaScript 通过构造函数生成对象，因此构造函数可以视为对象的模板。实例对象的属性和方法，可以定义在函数的内部
function Cat(name, color) {
    this.name = name;
    this.color = color;
    this.meow = function () {
        console.log("mew mew, my name is" + this.name, " and my color is " + this.color);
    }
}

let cat = new Cat("Tom", "red");
cat.meow();

// 但是这存在一个问题，方法属性 meow 会在 cat 和 cat_1 两个对象中同时存在，从建模的角度看，meow 应该是所有
// Cat 实例共同拥有的，也就是 has-a 的关系
let cat_1 = new Cat("Tim", "Black");

//为了解决这种问题，JS引入了原型机制——JS 的继承机制，不是基于Class实现的，而是基于原型对象。原型对象的的所有属性和方法
//都能被实例对象共享


// 原型对象
function test_prot() {

}

// 原型对象，也是一个对象，函数 test_prot 的 prototype 指针指向了这个对象
console.log(typeof test_prot.prototype);

// 对于普通函数来说，基本用不上这个属性，所以业务开发场景中基本用不到
// 但是对于构造函数来说，生成实例的时候，该属性会自动成为实例对象的原型
let cat_2 = new Cat("Tina", "Yellow");
console.log(cat_2.color);
Cat.prototype.name = "Black";
console.log(cat_2.color);

