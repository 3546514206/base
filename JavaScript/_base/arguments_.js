var f = function (one) {
    console.log(arguments[0]);
    console.log(arguments[1]);
    console.log(arguments[2]);

    console.log(arguments.callee === f);
}

f(1, 2, 3)