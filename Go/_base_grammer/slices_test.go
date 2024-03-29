package main

import (
	"fmt"
	"testing"
)

// 切片的简单测试
func TestSlices(t *testing.T) {
	var s []string
	fmt.Println("uninit:", s, s == nil, len(s) == 0)

	s = make([]string, 3)
	fmt.Println("emp:", s, " len:", len(s), " cap:", cap(s))

	s[0] = "a"
	s[1] = "3"
	s[2] = "c"

	fmt.Println("set:", s)
	fmt.Println("get", s[2])

	fmt.Printf("addr%p", &s)
	s = append(s, "ddd")
	fmt.Println()
	fmt.Printf("addr%p", &s)
	fmt.Println()

}
