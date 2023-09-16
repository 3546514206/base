package main

import (
	"fmt"
	"math"
	"runtime"
)

// 无限创建协程
func main() {
	taskCnt := math.MaxInt64

	for i := 0; i < taskCnt; i++ {
		go func(index int) {
			fmt.Println("go func ", i, "goroutine count = ", runtime.NumGoroutine())
		}(i)
	}
}
