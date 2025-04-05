package main

import "fmt"

func main() {

	number := 42
	pointer := &number

	// 数值
	fmt.Println(number)
	// 地址
	fmt.Println(pointer)

	// 解引用直接操作值
	*pointer = 100
	// 值变了
	fmt.Println(number)
	// 地址不变
	fmt.Println(pointer)
	// 函数通过指针修改值
	modifyValue(pointer)
	fmt.Println(number)
}

func modifyValue(ptr *int) {
	*ptr = 200
}
