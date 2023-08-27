package main

import (
	"./myMath"
	"fmt"
)

func main() {
	fmt.Println("do something")
	a := myMath.Add(3, 4)
	fmt.Println(a)
	b := myMath.Multi(5, 7)
	fmt.Println(b)
}
