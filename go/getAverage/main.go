package main

import "fmt"

func main() {
	var balance = [5]int{100, 66, 2, 3, 17}
	var avg float32
	
	avg = getAverage(balance)
	
	fmt.Println(avg)
}

func getAverage(balance [5]int) float32 {
	var size int = len(balance)
	var index int = 0
	var sum int = 0
	
	for index < size {
		sum += balance[index]
		index++
	}
	
	return float32(sum) / float32(size)
}
