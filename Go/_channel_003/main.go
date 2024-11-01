package main

import "fmt"

func f(n int, c chan int) {
	x := 100
	for index := 0; index < n; index++ {
		fmt.Printf("开始发送%d\n", x)
		c <- x
		fmt.Printf("结束发送%d\n", x)
		x++
	}
	close(c)
}

func main() {

	// 首先，通道的发送和接收操作是相互独立的，不会阻塞对方的执行，
	// 除非通道满了（对于发送操作）或空了（对于接收操作）。

	// 具体地看，当通道不带缓存时，通道的容量为1，当子协程完成了第一次发送操作，可以直接进行其余计算并
	// 准备第二次发送操作，但是由于通道容量为1，
	// 必须等待主协程完成数据接收才能继续第二次发送
	//c := make(chan int)
	// 如果通道容量为8，则子协程可以一次性往通道内最少发送8个数据二不被阻塞，如果主协程同时在接收处
	// 理数据，理论上可以达到一个动态平衡的过程，假设发送过程无限循环，子协程的发送过程可以永远也不
	// 被阻塞（理想状况下，因为发送与接收过程互不干扰）
	c := make(chan int, 8)
	go f(10, c)
	for value := range c {
		fmt.Printf("接收%d\n", value)
	}
}
