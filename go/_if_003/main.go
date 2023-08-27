package main

import "fmt"

func main() {

	var count int
	var flag bool

	count = 1

	//go没有while
	//while(count<100) {
	for count < 100 {
		count++
		flag = true

		for index := 2; index < count; index++ {
			if count%index == 0 {
				flag = false
			}
		}

		if flag {
			fmt.Println(count, "is prime")
		}
	}
}
