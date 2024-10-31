// 在泛型中，通常使用一些约定俗成的标识符，比如常见的 T（表示 Type）、U、V 等，但实际上你可以使用任何标识符。
// T: 代表 "Type"，是最常见的泛型类型参数名。
function identity<T>(a: T): T {
    return a;
}

console.log(identity("sss"));


// E: 用于表示数组元素的泛型类型参数。
function printArray<E>(arr: E[]): void {
    arr.forEach(x => console.log(x));
}

printArray([1, 2, 3, 4, 5]);

// K, V: 用于表示键（Key）和值（Value）的泛型类型参数。
interface KeyValuePair<K, V> {
    key: K,
    value: V
}

let pair: KeyValuePair<string, number> = {
    key: "age",
    value: 12
}

console.log(pair);

// 声明实现该接口的泛型类
class KeyValue<K, V> implements KeyValuePair<K, V> {
    key: K;
    value: V;

    constructor(key: K, value: V) {
        this.key = key;
        this.value = value;
    }

    display(): void {
        console.log(`Key: ${this.key}, Value: ${this.value}`);
    }
}

// 使用示例
const pair1 = new KeyValue<string, number>("age", 30);
pair1.display();

const pair2 = new KeyValue<number, boolean>(1, true);
pair2.display();

// R: 用于表示函数返回值的泛型类型参数。
function getResult<R>(value: R): R {
    return value;
}

// U, V: 通常用于表示第二、第三个泛型类型参数。
function combine<U, V>(first: U, second: V): string {
    return `${first} ${second}`;
}






