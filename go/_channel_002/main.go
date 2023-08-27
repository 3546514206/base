package main

import "fmt"

func main() {
	// 这里我们定义了一个可以存储整数类型的带缓冲通道
	// 缓冲区大小为2
	channel := make(chan int, 2)

	channel <- 1
	channel <- 1

	fmt.Println(<-channel)
	fmt.Println(<-channel)
	fmt.Println("已经全部返回")
	//fmt.Println(<-channel)
	fmt.Println("能结束么？")
}
