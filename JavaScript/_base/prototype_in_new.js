function Animal(name) {
    this.name = name;
}

// 使用 new 操作符创建 Animal 的实例
var myAnimal = new Animal("Fluffy");

// 在 Animal 的原型上添加一个方法
Animal.prototype.sayHello = function () {
    console.log("Hello, I'm " + this.name);
};

// 调用实例方法
// 往原型对象上新增一个方法的时候，实例对象执行该方法，如果没有找到该方法，就会找到这个原型对象上的同名方法来执行
myAnimal.sayHello(); // 输出: Hello, I'm Fluffy