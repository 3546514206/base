namespace testinterface {
    interface IPerson {
        name: string;

        age: number;

        // () => void：这是该方法的类型定义，包含以下几个部分：
        // ()：表示这个方法没有参数。如果方法有参数，它们会被列在这里。
        // =>：箭头符号，用于表示方法的返回类型。
        // void：表示方法没有返回值，即调用该方法时不会返回任何数据。
        say: () => void;

        // 在接口中定义方法的另一种方式
        run(): void;
    }

    let employ: IPerson = {
        name: "setsuna",

        age: 21,

        say: () => {
            console.log(`my name is ${employ.name},my age is ${employ.age}`);
        },

        run() {
            console.log("running");
        }
    }

    employ.say();
    employ.run();
}