package main

import "fmt"

func main() {
	// go 语言中，声明数组一旦没有指定大小，则是切片
	numbers := []int{21, 12, 13, 54, 51, 62, 77, 18}
	fmt.Println(numbers)
	printSlice(numbers)
	//
	numbers = append(numbers, 29)
	printSlice(numbers)
}

func printSlice(numbers []int) {
	fmt.Printf("len=%d,cap=%d\n", len(numbers), cap(numbers))
}
