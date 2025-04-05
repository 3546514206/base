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

// BreakContinueFor for 循环的过程，可以使用 continue 来跳过当次循环，使用 break 中断循环。
func BreakContinueFor() {

	for i := 0; i < 100; i++ {
		if i%2 == 0 {
			continue
		}

		// 大于 10 就中断循环
		if i > 10 {
			break
		}

		fmt.Printf("%d", i)
	}
	fmt.Println()
}

// ForRange for-range 用来迭代字符串、数组、切片等类型，index 是迭代的索引，类似循环变量，ch 是拷贝的迭代值，
// for-range 有一些要注意的知识点和坑，以后会经常用到它。
func ForRange() {

	str := "this is a test string"

	for index, ch := range str {
		// 跳过空格不处理
		if ch != 0x20 {
			fmt.Printf("%d%c ", index, ch)
		}
	}

	fmt.Println()
}

// ForRange2 如果不关心循环的具体值，可以不写 for-range 的第二个循环变量。
func ForRange2() {
	str := "this is a test"
	for index := range str {
		fmt.Printf("%d ", index)
	}
	fmt.Println()
}

// ForRange3 如果不关心循环的次数，index 却不能不写，但是可以使用 _ 占位符来代替，表示不关心它具体的值。
func ForRange3() {
	str := "this is a test"
	for _, ch := range str {
		fmt.Printf("%c", ch)
	}
	fmt.Println()
}

// DeadSelect select 分支语句是 go 语言非常重要的分支判断语句，在学了通道以后会用的特别多，也是有别于其他语言的一个语法。
// select 带有多个 case，每个 case 都是一个 IO 操作，select 随机的选择满足条件的执行一个，重点来了，
// 如果没有 case 可以运行，它将发生阻塞,一直等到有一个 case 满足条件位置。所以下面的代码是一个死循环，一直会阻塞，
// 因为它没有 case，永远都不会满足一个。
func DeadSelect() {
	select {}
}
