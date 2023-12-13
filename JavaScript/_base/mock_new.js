// 模拟 new 是如何执行一个构造函数的
function _new(
    /* 构造函数 */
    constructor,
    /* 构造函数参数 */
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

// 实例
var actor = _new(Person, '张三san', 28);
// 打印对象
console.log(actor.valueOf())


function Dog(name) {
    this.name = name
}

var d = new Dog("Tina");