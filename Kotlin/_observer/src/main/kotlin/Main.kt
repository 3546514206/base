// 主题（Subject）接口
interface Subject {
    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

// 具体主题（ConcreteSubject）类
class ConcreteSubject : Subject {
    private val observers = mutableListOf<Observer>()
    private var state: String = ""

    fun setState(newState: String) {
        state = newState
        notifyObservers()
    }

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.update(this) }
    }

    fun getState(): String {
        return state
    }
}

// 观察者（Observer）接口
interface Observer {
    fun update(subject: Subject)
}

// 具体观察者（ConcreteObserver）类
class ConcreteObserver(private val name: String) : Observer {
    override fun update(subject: Subject) {
        if (subject is ConcreteSubject) {
            println("Observer $name received update. New state: ${subject.getState()}")
        }
    }
}

// 示例
fun main() {
    val subject = ConcreteSubject()

    val observer1 = ConcreteObserver("Observer1")
    val observer2 = ConcreteObserver("Observer2")

    subject.addObserver(observer1)
    subject.addObserver(observer2)

    subject.setState("New State 1")
    // 输出:
    // Observer Observer1 received update. New state: New State 1
    // Observer Observer2 received update. New state: New State 1

    subject.setState("New State 2")
    // 输出:
    // Observer Observer1 received update. New state: New State 2
    // Observer Observer2 received update. New state: New State 2
}
