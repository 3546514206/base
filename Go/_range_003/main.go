package main

import "fmt"

func main() {

	myMap := make(map[int]float32)

	myMap[1] = 11.0
	myMap[2] = 22.0
	myMap[3] = 33.0
	myMap[4] = 44.0
	myMap[5] = 55.0

	for key, value := range myMap {
		fmt.Printf("key is:%d,value is:%f\n", key, value)
	}
	fmt.Println()

	for key, _ := range myMap {
		fmt.Printf("key is:%d\n", key)
	}
	fmt.Println()

	for _, value := range myMap {
		fmt.Printf("value is:%f\n", value)
	}
}
