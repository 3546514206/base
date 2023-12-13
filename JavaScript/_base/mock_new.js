// 模拟 new 是如何执行一个构造函数的
// s1:创建一个新的空对象
// s2:将这个空对象与当前执行上下文的this指针指向该空对象
// s3:将该对象的原型属性（__proto__）指向构造函数的原型属性，构造新的原型链
// s4:执行构造函数预定义的初始化逻辑
// 如果当前构造函数没有显式返回一个对象，则将this返回，返回this是隐式的；如果当前对象显式返回了一个对象，则new得到的是这个对象，而不是this指向的对象。
function _new(/* 构造函数 */
              constructor, /* 构造函数参数 */
              params) {

    // 将 arguments 对象转为数组
    var args = [].slice.call(arguments);
    // 取出构造函数
    var constructor = args.shift();
    // 创建一个空对象，继承构造函数的 prototype 属性
    var context = Object.create(constructor.prototype);
    // 执行构造函数
    var result = constructor.apply(context, args);
    // 如果返回结果是对象，就直接返回，否则返回 context 对象
    return (typeof result === 'object' && result != null) ? result : context;
}

function Person(name, age) {
    this.name = name;
    this.age = age;
}

function doCreateInstanceByMock() {
    let instance = _new(Person, "张三", 21);
    console.log(instance.valueOf());
}

function doCreateInstanceByNew() {
    let instance = new Person("张四", 22);
    console.log(instance.valueOf());
}

doCreateInstanceByMock();
doCreateInstanceByNew();



