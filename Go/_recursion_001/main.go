package main

import "fmt"

var index int = 0

func f() {
	fmt.Println(index)
	index++
	f()
}

func main() {
	f()
}
