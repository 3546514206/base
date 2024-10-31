namespace testimplements {
    interface IPersonV2 {
        name: string;
        age: number;
    }

    class Chef implements IPersonV2 {

        age: number;
        name: string;

        constructor(name: string, age: number) {
            this.name = name;
            this.age = age;
        }

        cook(): void {
            console.log(this.name);
            console.log(this.age);
            console.log("cooking!!!");
        }
    }

    let pe: Chef = new Chef("test", 21);
    pe.cook();
}