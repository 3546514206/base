package main

import "fmt"

func main() {

	strings := []string{"google", "runoob"}

	for i, s := range strings {
		fmt.Println(i, s)
	}

	// 第六个值是0值
	numbers := [6]int{1, 2, 3, 4, 5}
	for i, x := range numbers {
		fmt.Println(i, x)
	}
}
