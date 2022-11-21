
function Hello() {
    let name = "杨海波";
    this.setName = function(thyName) {
        name = thyName;
    };
    this.sayHello = function() {
        console.log('Hello ' + name);
    };
}

module.exports = Hello;
