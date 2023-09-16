package main

import "fmt"

var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}

func main() {

	for index, value := range pow {
		fmt.Printf("%d的二次幂=%d\n", index, value)
	}
}
