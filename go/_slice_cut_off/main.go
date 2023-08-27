package main

import "fmt"

func main() {

	originalSlice := []int{0, 1, 2, 3, 4, 5, 6, 7, 8}
	newSlice := originalSlice[3:6]

	fmt.Println("Original Slice:", originalSlice)
	fmt.Println("New Slice:", newSlice)
	fmt.Println("Capacity of Original Slice:", cap(originalSlice))
	fmt.Println("Capacity of New Slice:", cap(newSlice))
	fmt.Println()

	newSlice[1] = 44
	fmt.Println("Original Slice:", originalSlice)
	fmt.Println("New Slice:", newSlice)
	fmt.Println()

	originalSlice[5] = 55
	fmt.Println("Original Slice:", originalSlice)
	fmt.Println("New Slice:", newSlice)
}
