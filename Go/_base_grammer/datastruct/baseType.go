package datastruct

import "fmt"

func DefineArray() {
	// 申明
	var arr1 [3]int
	fmt.Println(arr1)
	// 申明并设置初始值
	var arr2 [3]int = [3]int{1, 2, 3}
	fmt.Println(arr2)
	// 推导
	arr3 := [3]int{1, 2, 3}
	fmt.Println(arr3)
	// 根据初始值来分配
	arr4 := [...]int{1, 2, 3}
	fmt.Println(arr4)

	// 等效于 [5]int{0, 2, 3, 0, 0}
	// 数组的大小是5，第 2 个元素的值是 2，第 3 个元素的值是 3，没有指定的就是 int 零值(0)可以
	arr5 := [5]int{1: 2, 2: 3}
	fmt.Println(arr5)
}
