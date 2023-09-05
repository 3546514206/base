package main

import "fmt"

type Calculator struct{}

//func (calculator Calculator) Add(a, b int) int {
//	return a + b
//}

// 下面的方法定义会导致编译错误，因为方法名和参数列表与上面的Add方法相同
func (calculator Calculator) Add(a, b, c int) int {
	return a + b + c
}

func main() {
	calculator := Calculator{}
	//sum := calculator.Add(1, 2)
	sum := calculator.Add(1, 2, 3)
	fmt.Println("Sum:", sum)
}
