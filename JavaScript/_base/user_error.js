function UserError(message) {
    this.message = message || '默认信息';
    this.name = 'UserError';
}

function throwIt(message) {
    throw new UserError(message);
}

try {
    throwIt('do it');
} catch (e) {
    console.log(e.message)
}
