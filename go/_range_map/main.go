package main

import (
	"fmt"
)

func main() {
	myMap := make(map[int]float32)
	myMap[1] = 3.0
	myMap[2] = 4.0
	myMap[3] = 6.0
	myMap[4] = 9.0
	myMap[5] = 12.0
	myMap[6] = 23.0
	myMap[7] = 35.0
	
	for key, value := range myMap {
		fmt.Println(key, " : ", value)
	}
	
	fmt.Println("__________________")
	
	for key := range myMap {
		fmt.Println(myMap[key])
	}
	
	fmt.Println("__________________")
	
	for _, value := range myMap {
		fmt.Println(value)
	}
}
