package cotrol

import "fmt"

// CanBuy 可不可以买
func CanBuy(hasMoney, productPrice int) {
	// if 后面不需要括号，if 的执行体无论是否为空 { } 都是必须的
	if hasMoney >= productPrice {
		fmt.Printf("you can, will %d left\n", hasMoney-productPrice)
	}
}

// CheckPrice 判断价格
func CheckPrice(amount int) {
	if amount > 50 {
		if amount > 100 {
			fmt.Println("太贵了")
		} else {
			fmt.Println("还行")
		}
	} else {
		fmt.Println("真便宜啊")
	}
}

// FullFor 完整的 For 循环
func FullFor() {
	sum := 0

	for i := 0; i < 100; i++ {
		sum = sum + i
	}

	fmt.Println(sum)
}

// ConditionFor 只有循环条件，初始值和增减量是可以没有，这时候两边的分号也可以不写了。
func ConditionFor() {
	sum := 1

	for sum < 10 {
		sum += sum
	}

	fmt.Println(sum)
}
