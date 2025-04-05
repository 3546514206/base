// 1、全局上下文指向空对象
{
    // 全局的 this 指向一个特殊的空对象（不是global，在浏览器环境则是指向windows）。这个对象在模块加载时被创建，
    // 作为全局上下文中的 this 的默认值。这样设计有助于防止全局变量的滥用和污染。
    console.log(this);
}

// 2、普通函数中的this指向全局对象
{
    // 在函数执行上下文中，this指向全局对象，在浏览器环境中，指向 window,而在node 环境中指向 global对象
    function doSomeThing() {
        console.log(this);
    }

    doSomeThing();
}

// 3、普通函数作为对象的方法时，this指针指向了该对象
{
    function exampleFunction() {
        console.log(this);
    }

    // 作为普通函数调用时，this指向全局对象
    exampleFunction();

    const obj = {
        method: exampleFunction
    };
    // 作为对象的方法被调用的时候，this指向 obj 对象
    console.log(obj.method());
}

// 4、在构造函数中，this 指针指向构造函数创建的新对象（必须是以new的方式执行，否则按照普通函数论处）
{
    function ExampleConstructor() {
        this.property = 'value';
    }

    const instance = new ExampleConstructor();
    console.log(instance.property);
}

// 5、在事件处理函数中，this 通常指向触发事件的元素。
{
    const EventEmitter = require('events');

    class MyEmitter extends EventEmitter {
    }

    const myEmitter = new MyEmitter();

    myEmitter.on('myEvent', function () {
        // 指向 myEmitter 实例
        console.log(this);
    });

    myEmitter.emit('myEvent');
}

// 6、箭头函数的 this 与外围最近一层非箭头函数的执行上下文中的 this 保持一致，即箭头函数没有自己的 this。
{
    const arrowFunction = () => {
        // 与外围上下文中的 this 保持一致
        console.log(this);
    };

    function execArrowFunction() {
        // 取决于外围上下文的 this
        arrowFunction();
    }


    const arrowObj = {
        arrowMethod: arrowFunction
    }

    execArrowFunction();
    arrowObj.arrowMethod();
}
