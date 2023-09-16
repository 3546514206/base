package main

import "fmt"

// 一个可以返回多个值的函数
func numbers() (int, int, string) {
	a, b, c := 1, 2, "testString"
	return a, b, c
}

func main() {
	//只获取函数返回值的两个
	num, _, str := numbers()
	fmt.Println(num, str)
}
