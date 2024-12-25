package datastruct

import (
	"fmt"
	"time"
	"unsafe"
)

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

func sliceSize() {
	// 构造空的切片
	var s1 []int
	// 24
	fmt.Println(unsafe.Sizeof(s1))
}

func createSliceFromArray() {
	arr1 := [5]int{1, 2, 3, 4, 5}
	s1 := arr1[:]
	fmt.Printf("%T\n%T\n", arr1, s1) // [5]int []int
}

func DefineBook() {
	book := struct {
		Name    string
		Author  string
		Publish time.Time
	}{"算法导论", "大神", time.Now()}
	// struct { Name string; Author string; Publish time.Time }
	fmt.Printf("%T\n", book)
	fmt.Println(book)
}
