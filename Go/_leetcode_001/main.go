package main

import "fmt"

func main() {
	nums := []int{2, 7, 11, 15}
	target := 9
	
	fmt.Println(twoSum(nums, target))
}

func twoSum(nums []int, target int) []int {
	// 创建一个 map 用于存储数字和它们的索引
	numMap := make(map[int]int)
	
	// 遍历数组
	for i, num := range nums {
		// 计算需要的补数
		complement := target - num
		// 在 map 中查找补数是否存在
		index, found := numMap[complement]
		if found {
			// 如果存在，返回补数的索引和当前数字的索引
			return []int{index, i}
		}
		// 如果补数不存在，将当前数字及其索引存入 map
		numMap[num] = i
	}
	
	// 如果没有找到符合条件的结果，返回一个空数组
	return nil
}
