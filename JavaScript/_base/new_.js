// 在 JavaScript 中，new 关键字用于创建对象实例。使用 new 关键字调用构造函数时，会执行以下步骤：
//
// 创建一个空对象。
// 将这个空对象的 __proto__ 指向构造函数的原型对象（prototype）。
// 将构造函数内部的 this 指向这个新创建的对象。
// 执行构造函数内部的代码，为新对象添加属性和方法。
// 如果构造函数返回了一个对象，则返回该对象；否则，返回新创建的对象。
function Car(make, model) {
    this.make = make;
    this.model = model;
}

// 使用 new 关键字创建 Car 的实例
const myCar = new Car('Toyota', 'Camry');

console.log(myCar.make);  // 输出 "Toyota"
console.log(myCar.model); // 输出 "Camry"


function Person(name) {
    this.name = name;

    // 如果构造函数显式返回一个对象，则 new 的结果将是这个对象，而不是新创建的对象。
    return {greeting: 'Hello from the returned object!'};
}

const personInstance = new Person('John');
console.log(personInstance.name);  // undefined，因为构造函数返回的是一个新对象，而不是新创建的对象
console.log(personInstance.greeting);  // 输出 "Hello from the returned object!"
