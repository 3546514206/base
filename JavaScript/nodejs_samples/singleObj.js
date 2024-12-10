class User {
    constructor(name) {
        this.name = name;
    }

    getName() {
        return this.name;
    }
}


let Proxy = (function () {
    let instance = null;
    return function (name) {
        if (!instance) {
            instance = new User(name);
        }
        return instance;
    }
})();

let a = new Proxy("AAA");
let b = new Proxy("BBB");

console.log(a === b);
console.log(a.getName());
console.log(b.getName());
