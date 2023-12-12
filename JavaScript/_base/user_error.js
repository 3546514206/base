function UserError(message) {
    this.message = message || '默认信息';
    this.name = 'UserError';
}

try {
    throw new UserError();
} catch (e) {
    console.log(e.message)
}
