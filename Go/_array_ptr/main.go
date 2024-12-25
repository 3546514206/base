package main

import "fmt"

const MAX = 3

func main() {

	a := [MAX]int{10, 100, 200}
	var ptr [MAX]*int

	for index := 0; index < MAX; index++ {
		ptr[index] = &a[index]
	}

	for index := 0; index < MAX; index++ {
		fmt.Println(ptr[index])
		fmt.Println(*ptr[index])
	}
}
