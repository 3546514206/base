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