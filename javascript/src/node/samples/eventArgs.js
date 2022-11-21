let Emitter = require("events").EventEmitter;

let emitter = new Emitter();

emitter.on("click", function (arg) {
    console.log(arg);
});

emitter.on("click", function (arg1, arg2) {
    console.log(arg1 + " : " + arg2);
});

emitter.on("click", function (arg1, arg2) {
    console.log(arg1 + " + " + arg2);
});

setTimeout(function () {
    emitter.emit("click", 1);
}, 5000);

let moveEventListener = function () {
    console.log("handling moving event");
}

emitter.addListener("move", moveEventListener);

setTimeout(function () {
    emitter.emit("move");
},1000);
