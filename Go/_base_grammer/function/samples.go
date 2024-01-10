package function

import "fmt"

// 完整的函数包含函数名称、参数列表、返回值、函数体。如果一个函数没有参数，也没有返回值，就只剩下函数名和函数体了。
func doNothing() {
	// 函数体
}

// DoSomething 函数
func DoSomething() {
	anonymous()
}

// 直接调用一个匿名函数
func anonymous() {
	func() {
		fmt.Println("i am anonymous function")
	}() // 注意这个括号
}
