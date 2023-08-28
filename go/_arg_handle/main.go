package main

import (
	"flag"
	"fmt"
)

// go run main.go -port=9090 -debug
func main() {
	// 定义命令行参数
	port := flag.Int("port", 8080, "Port number")
	debug := flag.Bool("debug", false, "Enable debugging")

	// 解析命令行参数
	flag.Parse()

	// 使用解析后的命令行参数
	fmt.Println("Port:", *port)
	fmt.Println("Debug:", *debug)
	fmt.Println("Other arguments:", flag.Args())
}
