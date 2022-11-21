// 引入 events 模块
let Emitter = require('events').EventEmitter;

// 创建 eventEmitter 对象
let emitter = new Emitter();

emitter.on("click", function () {
    console.log("handling click event");
});

setTimeout( function () {
    emitter.emit('click');
},5000)

setTimeout( function () {
    emitter.emit('click');
},10000)