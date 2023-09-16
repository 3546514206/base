package main

import (
	"fmt"
	"net"
	"strconv"
	"time"
)

func main() {
	fmt.Println("client test ... start")
	time.Sleep(3 * time.Second)
	
	conn, err := net.Dial("tcp", "127.0.0.1:7077")
	if err != nil {
		fmt.Println("client start error,exit!")
		return
	}
	
	index := 0
	for true {
		
		value := "hello " + strconv.Itoa(index)
		_, err = conn.Write([]byte(value))
		index++
		
		if err != nil {
			fmt.Println("write error,err:", err)
			return
		}
		
		buf := make([]byte, 512)
		cnt, err := conn.Read(buf)
		
		if err != nil {
			fmt.Println("read error, exit, error", err)
		}
		
		fmt.Printf("server call back now,the content is %s, lenth is %d \n", buf, cnt)
	}
}
