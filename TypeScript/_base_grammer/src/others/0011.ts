function identity<T>(a: T): T {
    return a;
}

console.log(identity("sss"));


function printArray<E>(arr: E[]): void {
    arr.forEach(x => console.log(x));
}

printArray([1, 2, 3, 4, 5]);

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