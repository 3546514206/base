interface IPerson {
    firstName: string,
    lastName: string,
    sayHi: () => string
}

var customer: IPerson = {
    firstName: "Tom",
    lastName: "Hanks",
    sayHi: function (): string  {
        return this.firstName + this.lastName;
    }
}

console.log("Customer 对象 ")
console.log(customer.firstName)
console.log(customer.lastName)
console.log(customer.sayHi())

var employee: IPerson = {
    firstName: "Jim",
    lastName: "Blakes",
    sayHi: (): string => {
        return "Hello!!!"
    }
}

console.log("Employee  对象 ")
console.log(employee.firstName)
console.log(employee.lastName)