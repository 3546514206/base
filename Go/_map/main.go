package main

import "fmt"

func main() {
	// 使用 make 函数初始化一个空 map
	myMap := make(map[string]int)

	// 向 map 中添加键值对
	myMap["apple"] = 10
	myMap["banana"] = 5
	myMap["cherry"] = 12

	value, exits := myMap["cherry"]

	if exits {
		fmt.Println(value)
	}

	// 访问 map 中的值
	fmt.Println("apple count:", myMap["apple"])
	fmt.Println("banana count:", myMap["banana"])
	fmt.Println("cherry count:", myMap["cherry"])

	// 删除 map 中的键值对
	delete(myMap, "banana")

	// 遍历 map 中的键值对
	for key, value := range myMap {
		fmt.Printf("%s -> %d\n", key, value)
	}
}
