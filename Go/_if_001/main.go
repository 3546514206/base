package main

import "fmt"

func main() {
	var a int = 345

	if a < 20 {
		fmt.Println("a < 20")
	}

	formatStr := fmt.Sprintf("a = %d", a)
	fmt.Println(formatStr)

}
