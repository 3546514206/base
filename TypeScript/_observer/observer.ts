// 主题接口
interface Subject {
    addObserver(observer: Observer): void;

    removeObserver(observer: Observer): void;

    notifyObservers(): void;
}

// 观察者接口
interface Observer {
    update(data: any): void;
}

// 具体主题实现
class ConcreteSubject implements Subject {
    private observers: Observer[] = [];
    private state: any;

    addObserver(observer: Observer): void {
        this.observers.push(observer);
    }

    removeObserver(observer: Observer): void {
        const index = this.observers.indexOf(observer);
        if (index !== -1) {
            this.observers.splice(index, 1);
        }
    }

    notifyObservers(): void {
        for (const observer of this.observers) {
            observer.update(this.state);
        }
    }

    setState(newState: any): void {
        this.state = newState;
        this.notifyObservers();
    }
}

// 具体观察者实现
class ConcreteObserver implements Observer {
    private name: string;

    constructor(name: string) {
        this.name = name;
    }

    update(data: any): void {
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
