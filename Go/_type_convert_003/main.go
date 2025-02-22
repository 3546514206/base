package main

import (
	"fmt"
	"strconv"
)

func main() {
	num := 3.14
	str := strconv.FormatFloat(num, 'f', 2, 64)
	fmt.Printf("浮点数 %f 转为字符串为：'%s'\n", num, str)
}
