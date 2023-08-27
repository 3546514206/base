package main

import (
	"fmt"
	"strconv"
)

func say(str string) {
	for index := 0; index < 10; index++ {
		fmt.Println(str + " " + strconv.Itoa(index))
	}
}

func main() {
	// 打印没有固定顺序，次数也不是 20 次
	go say("world")
	say("hello")

}
