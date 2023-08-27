package main

import "fmt"

func main() {
	// 局部变量定义
	var a int = 100

	if a < 20 {
		fmt.Println("<20")
	} else {
		fmt.Println(">=20")
	}

	fmt.Println(a)
}
