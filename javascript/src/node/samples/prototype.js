let Person = function () {

};

let person = new Person();
// let person2 = new Person();
//console.log(typeof Person);
// 构造函数
// console.log(Person.constructor);
//console.log(typeof Person.constructor);
//console.log(typeof person);
//console.log(typeof person.__proto__);
// 只有函数才有prototype属性
//console.log(typeof Person.prototype);
// 实例对象不存在prototype属性
//console.log(person.prototype);
//console.log(person.__proto__ === Person.prototype);
//console.log(Person.constructor === Function);
//console.log(Person.prototype.constructor === Person);
//
// console.log(person1.age);
// console.log(person2.age);
// Person.prototype.age = 11;
// console.log(person1.age);
// console.log(person2.age);
// person1.age = 21;
// console.log(person1.age);
// console.log(person2.age);
//
// console.log(typeof person1);
// console.log(typeof Person);

//es5获取原型对象的方法
console.log(Object.getPrototypeOf(person1));
console.log(Object.getPrototypeOf(Person));

