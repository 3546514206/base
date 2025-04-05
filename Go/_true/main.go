package main

import "fmt"

func main() {
	var num int = 0
	if num != 0 {
		fmt.Println("num is not zero")
	} else {
		fmt.Println("num is zero")
	}

	var str string = "Hello"
	if str != "" {
		fmt.Println("str is not empty")
	} else {
		fmt.Println("str is empty")
	}

	var slice []int
	if len(slice) > 0 {
		fmt.Println("slice is not empty")
	} else {
		fmt.Println("slice is empty")
	}

	var ptr *int
	if ptr != nil {
		fmt.Println("ptr is not nil")
	} else {
		fmt.Println("ptr is nil")
	}

}
