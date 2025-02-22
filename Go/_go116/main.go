package main

import (
	"fmt"
	"runtime"
)

func main() {
	// 获取 Go 版本
	fmt.Println("Go version:", runtime.Version())
	
	// 获取操作系统和架构信息
	fmt.Println("OS/Arch:", runtime.GOOS, runtime.GOARCH)
	
	// 获取 CPU 核心数
	fmt.Println("CPU Cores:", runtime.NumCPU())
	
	// 获取 Goroutine 数量
	fmt.Println("Goroutines:", runtime.NumGoroutine())
}
