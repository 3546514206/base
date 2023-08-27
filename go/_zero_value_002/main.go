package main

import "fmt"

func main() {
	var a1 *int
	var a2 []int
	var a3 map[string]int
	var a4 chan int
	var a5 func(string) int
	// error 是接口
	var a6 error

	fmt.Println(a1, a2, a3, a4, a5, a6)
}
