package main

// 节点定义
type HashMap struct {
	key      string
	value    string
	hashCode int
	// 链地址法
	next *HashMap
}

// 哈希表
var tables = make(map[string]string, 16)

// 哈希值计算
func getHashCode(k string) int {
	if len(k) == 0 {
		return 0
	}
	
	hashCode := 0
	lastIndex := len(k) - 1
	for index, char := range k {
		if index == lastIndex {
			hashCode += int(char)
			break
		}
		hashCode += (hashCode + int(k[index])) * 31
	}
	
	return hashCode
}
func indexTable(hashCode int) int {
	return hashCode % 16
}

func indexNode(hashCode int) int {
	return hashCode >> 4
}

func main() {
	
}
