# 主题（Subject）类，被观察的对象
class Subject
  attr_accessor :observers, :state

  def initialize
    @observers = []
  end

  def add_observer(observer)
    observers << observer
  end

  def remove_observer(observer)
    observers.delete(observer)
  end

  def notify_observers
    observers.each { |observer| observer.update(self) }
  end
end

# 具体主题（ConcreteSubject）类，实现具体的业务逻辑
class ConcreteSubject < Subject
  def set_state(state)
    @state = state
    notify_observers
  end
end

# 观察者（Observer）类，定义更新接口
class Observer
  def update(subject)
    raise NotImplementedError, "#{self.class} 未实现 'update' 方法."
  end
end

# 具体观察者（ConcreteObserver）类，实现具体的更新逻辑
class ConcreteObserver < Observer
  def update(subject)
    puts "观察者收到通知，主题状态已更新为 #{subject.state}"
  end
end

# 示例
subject = ConcreteSubject.new

observer1 = ConcreteObserver.new
observer2 = ConcreteObserver.new

subject.add_observer(observer1)
subject.add_observer(observer2)

subject.set_state("新状态")
# 观察者收到通知，主题状态已更新为 新状态
# 观察者收到通知，主题状态已更新为 新状态
