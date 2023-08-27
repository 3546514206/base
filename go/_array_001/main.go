package main

import "fmt"

func main() {
	
	var numbers [5]int
	fmt.Println(numbers)
	
	var numbers_001 = [5]int{1, 2, 3, 4, 5}
	fmt.Println(numbers_001)
	
	var balance = [5]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
	fmt.Println(balance)
	
	balance_001 := [5]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
	fmt.Println(balance_001)
	
	var balance_002 = [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
	fmt.Println(balance_002)
	
	balance_003 := [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
	fmt.Println(balance_003)
	
	//  将索引为 1 和 3 的元素初始化
	balance_004 := [5]float32{1: 2.0, 3: 7.0}
	fmt.Println(balance_004)
}
