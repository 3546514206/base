package main

import (
	"fmt"
	"net"
	"os"
	"time"
)

func main() {
	if len(os.Args) != 2 {
		fmt.Println("Usage: ", os.Args[0], "host")
		os.Exit(1)
	}

	host := os.Args[1]
	addr, err := net.ResolveIPAddr("ip", host)
	if err != nil {
		fmt.Println("Error resolving address: ", err)
		os.Exit(1)
	}

	conn, err := net.DialIP("ip4:icmp", nil, addr)
	if err != nil {
		fmt.Println("Error opening connection: ", err)
		os.Exit(1)
	}
	defer conn.Close()

	fmt.Println("PING", host)
	fmt.Println("-------------------------------")

	for i := 0; i < 4; i++ {
		message := []byte("Hello!")
		startTime := time.Now()

		conn.SetDeadline(startTime.Add(3 * time.Second))
		_, err = conn.Write(message)
		if err != nil {
			fmt.Println("Error writing to connection: ", err)
			continue
		}

		buffer := make([]byte, 512)
		_, err = conn.Read(buffer)
		if err != nil {
			fmt.Println("Error reading from connection: ", err)
			continue
		}

		duration := time.Since(startTime)
		fmt.Printf("Reply from %s: time=%v\n", addr.String(), duration)
		time.Sleep(1 * time.Second)
	}

	fmt.Println("-------------------------------")
}
