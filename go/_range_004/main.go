package main

import "fmt"

func main() {
	nums := []int{1, 2, 4}
	sum := 0

	for _, num := range nums {
		sum += num
	}
	fmt.Println(sum)
	for index, num := range nums {
		if index == 2 {
			fmt.Printf("index:%d,value:%d\n", index, num)
		}
	}

	kvs := map[string]string{"0001": "apple", "0002": "banana", "0003": "pear"}

	for key, value := range kvs {
		fmt.Printf("%s => %s\n", key, value)
	}

	for index, value := range "gogogo" {
		fmt.Printf("idnex %d,value %c\n", index, value)
	}
}
