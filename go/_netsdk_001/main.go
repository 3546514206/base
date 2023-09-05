package main

import (
	"fmt"
	"net"
)

func main() {
	// 要解析的地址
	address := "example.com:80"

	// 解析 TCP 地址
	tcpAddr, err := net.ResolveTCPAddr("tcp", address)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	// 输出解析结果
	fmt.Println("Network:", tcpAddr.Network())
	fmt.Println("IP Address:", tcpAddr.IP.String())
	fmt.Println("Port:", tcpAddr.Port)
}
