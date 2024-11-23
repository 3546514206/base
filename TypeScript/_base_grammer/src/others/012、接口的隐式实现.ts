namespace implicit_implementation {
    interface Person {
        name: string;
        age: number;

        greet(): void;
    }

    // 隐式实现接口（没有使用 `implements` 关键字）
    class Developer {
        readonly name: string;
        readonly age: number;

        constructor(name: string, age: number) {
            this.name = name;
            this.age = age;
        }

        greet() {
            console.log(`Hello, I'm ${this.name}`);
        }
    }

    // 成功，符合 Person 的类型
    const dever: Person = new Developer("Alice", 30);

    dever.greet();
}