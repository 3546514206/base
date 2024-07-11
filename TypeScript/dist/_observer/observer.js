"use strict";
// 具体主题实现
class ConcreteSubject {
    constructor() {
        this.observers = [];
    }
    addObserver(observer) {
        this.observers.push(observer);
    }
    removeObserver(observer) {
        const index = this.observers.indexOf(observer);
        if (index !== -1) {
            this.observers.splice(index, 1);
        }
    }
    notifyObservers() {
        for (const observer of this.observers) {
            observer.update(this.state);
        }
    }
    setState(newState) {
        this.state = newState;
        this.notifyObservers();
    }
}
// 具体观察者实现
class ConcreteObserver {
    constructor(name) {
        this.name = name;
    }
    update(data) {
        console.log(`${this.name} received an update: ${data}`);
    }
}
// 使用例子
const subject = new ConcreteSubject();
const observer1 = new ConcreteObserver('Observer 1');
const observer2 = new ConcreteObserver('Observer 2');
subject.addObserver(observer1);
subject.addObserver(observer2);
subject.setState('New State 1');
// 输出:
// Observer 1 received an update: New State 1
// Observer 2 received an update: New State 1
subject.removeObserver(observer1);
subject.setState('New State 2');
// 输出:
// Observer 2 received an update: New State 2
//# sourceMappingURL=observer.js.map