let Emitter = require("events").EventEmitter;

let moveEventListener = function () {
    console.log("moving");
}

let emitter = new Emitter();

emitter.addListener("move", moveEventListener);

emitter.emit("move");

emitter.removeAllListeners();

// 没有监听器也不会被报错哦
emitter.emit("move");