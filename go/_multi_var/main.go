package main

import "fmt"

var x, y int

// 错误写法
// var c int, d string

// 这种因式分解关键字的写法一般用于声明全局变量
var (
	a int
	b string
)

var c, d int = 1, 2

// 声明变量同时直接赋值，此时可以省略类型信息，也会发生类型的自动推导
var e, f = 123, "hello"

//这种不带声明格式的只能在函数体中出现
//g, h := 123, "hello"
//i := 3

func main() {
	g, h := 123, "hello"
	fmt.Println(x, y, a, b, c, d, e, f, g, h)
}
