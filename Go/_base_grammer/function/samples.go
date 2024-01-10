package function

import "fmt"

// 完整的函数包含函数名称、参数列表、返回值、函数体。如果一个函数没有参数，也没有返回值，就只剩下函数名和函数体了。
func doNothing() {
	// 函数体
}

// DoSomething 函数
func DoSomething() {
	anonymous()
	fAnonymous("test")
	fmt.Println(incrNumber(3))

	arr := [...]int{4, 5, 6}
	// 第二个参数是不定参数，不能直接传递数组，如果传递数组，要按照下面的方式
	fmt.Println(sum(1, arr[:]...))

	builtInFunc()
}

// 直接调用一个匿名函数
func anonymous() {
	func() {
		fmt.Println("i am anonymous function")
	}() // 注意这个括号
}

// 把函数复制给一个变量
var fAnonymous = func(name string) {
	fmt.Printf("hey, %s\n", name)
}

// 面是一个有参数和返回值的函数，函数的返回结果使用 return 来表示。
func incrNumber(a int) int {
	return a + 1
}

// go 的函数也可以有多个返回值
func swapStr(s1 string, s2 string) (string, string) {
	return s2, s1
}

//  more 是不定参数，result 是返回变量。返回值可以申明一个具体的变量，这样返回的时候就只写 return 就可以了。
func sum(initial int, more ...int) (result int) {
	result = initial

	// 循环读取 more 参数，以后会学习
	for _, num := range more {
		result = result + num
	}

	// 等效于 return result
	return
}

func builtInFunc() {
	str := "english中文"
	// len 是一个内置函数
	// 对于 string 它返回字节
	// 返回 13，因为 1 个中文 UTF-8 这里存储占 3 个字节
	fmt.Println(len(str))
}
