// 利用闭包实现成员的私有属性
function Person(name) {
    var age;

    function setAge(n) {
        age = n;
    }

    function getAge() {
        return age;
    }

    return {
        name: name,
        getAge: getAge,
        setAge: setAge
    };
}

var p1 = new Person('张三');
p1.setAge(25);
console.log(p1.getAge()) // 25