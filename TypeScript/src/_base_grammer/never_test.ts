// let x_1: never;
let y: number;

// 编译错误，数字类型不能转为 never 类型
// x_1 = 123;

// x_1 = new Error('exception');
// console.log(x_1);

// 运行正确，never 类型可以赋值给 never类型
// x_1 = (()=>{ throw new Error('exception')})();

// 运行正确，never 类型可以赋值给 数字类型
y = (()=>{ throw new Error('exception')})();

// 返回值为 never 的函数可以是抛出异常的情况
function error(message: string): never {
    throw new Error(message);
}

// 返回值为 never 的函数可以是无法被执行到的终止点的情况
function loop(): never {
    while (true) {}
}