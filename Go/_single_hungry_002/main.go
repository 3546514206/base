package main

import "fmt"

// Singleton 定义饿汉式单例结构体
type Singleton struct {
	value int
}

// GetSingleton 获取单例对象
func GetSingleton() *Singleton {
	// 私有化构造函数，防止外部创建实例
	var instance Singleton
	// 初始化单例对象
	instance.value = 42

	return &instance
}

// ShowValue 展示单例对象值
func (s *Singleton) ShowValue() {
	fmt.Printf("单例对象值为：%d\n", s.value)
}

func main() {
	// 获取单例对象
	singleton1 := GetSingleton()
	// 展示单例对象值
	singleton1.ShowValue()

	// 再次获取单例对象
	singleton2 := GetSingleton()
	// 展示单例对象值
	singleton2.ShowValue()

	// 输出：
	// 单例对象值为：42
	// 单例对象值为：42
}
