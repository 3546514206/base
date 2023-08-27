package main

import "fmt"

func main() {
	// 创建一个初始容量为2的切片
	mySlice := make([]int, 0, 2)

	mySlice = append(mySlice, 100)
	mySlice = append(mySlice, 200)
	mySlice = append(mySlice, 300)

	fmt.Println("len=", len(mySlice))
	// 发生了动态扩容
	fmt.Println("cap=", cap(mySlice))
}
