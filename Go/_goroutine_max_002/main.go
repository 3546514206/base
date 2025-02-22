package main

import (
	"fmt"
	"math"
	"runtime"
)

func busi(ch chan bool, i int) {
	fmt.Println("go func ", i, " goroutine count = ", runtime.NumGoroutine())
	// 在写管道的过程中，实际上限制了协程的数量
	<-ch
}

func main() {
	taskCnt := math.MaxInt64
	
	ch := make(chan bool, 3)
	
	for index := 0; index < taskCnt; index++ {
		ch <- true
		go busi(ch, index)
	}
}
