// 闭包的最大用处有两个:
// 一个是可以读取外层函数内部的变量;
// 另一个就是让这些变量始终保持在内存中，即闭包可以使得它诞生环境一直存在。
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