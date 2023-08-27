package main

import "fmt"

func main() {

	m := make(map[string]int)
	m["a"] = 1
	m["b"] = 1
	m["c"] = 1
	m["d"] = 1
	m["b"] = 2

	fmt.Println(m)

	// 遍历 Map
	for k, v := range m {
		fmt.Printf("key=%s, value=%d\n", k, v)
	}
}
