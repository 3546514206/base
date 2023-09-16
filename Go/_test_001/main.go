// package 定义了一个包名，
// package main 表示一个独立可执行的程序，每个程序都需要有一个 main 的包
package main

// import 告诉编译器这个程序需要使用 fmt 包的元素
import "fmt"

// func main() 是程序开始执行的函数，和 C 族语言一样妈，main 函数是每个程序必须得
func main() {
	fmt.Println("我要和师姐一起学 GO 语言")
	DoSomething()
}

// 关于访问权限：
// 当标识符（包含常量、变量、类型、函数名、结构字段等等）以一个大写字母开头时，则是对外可见的
// 小写开头则是私有的
func DoSomething() {
	fmt.Print("testStr")
}
