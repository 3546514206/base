let event = require("events");

// 创建事件分发器
let eventEmitter = new event.EventEmitter();

// 事件处理程序
let connectHandler = function connected() {
    console.log('连接成功。');
}

// 绑定 connection 事件处理程序
eventEmitter.on('connection', connectHandler);

// 触发 connection 事件
eventEmitter.emit('connection');

console.log("程序执行完毕。");


