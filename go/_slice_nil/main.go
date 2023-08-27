package main

import "fmt"

func main() {
	var numbers []int

	printSlice(numbers)

	addr := &numbers

	fmt.Println(addr)
	fmt.Println(numbers)
	fmt.Println(numbers == nil)
}

func printSlice(numbers []int) {
	fmt.Printf("len=%d,cap=%d\n", len(numbers), cap(numbers))
}
