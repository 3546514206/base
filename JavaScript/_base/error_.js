var err = new Error('出错了');
console.log(err.message)

function throwit() {
    throw new Error('test');
}

function catchit() {
    try {
        throwit();
    } catch (e) {
        console.log(e.stack); // print stack trace
    }
}

catchit();