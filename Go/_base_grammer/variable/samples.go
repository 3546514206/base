package variable

import (
	"fmt"
	"unsafe"
)

// 有些数据在运行过程不会被改变或则防止被改变，使用常量更好，常量的意思就是申明赋值后就不会再改变了
// 常量类型也是可以推导值，下面等价:
const score int = 10
const score2 = 10

// CompileSizedof unsafe.Sizeof 计算字节大小，这个函数其实是在编译器就能确定的，它只和变量的类型有关系，和变量指向的缓冲区长度没有
// 关系，比如对于字符串来讲，因为 go 内部用 2 个字段来表示字符串类型，2 个字段各占用 8 个字节，所以对字符串使用 unsafe.Sizeof 返
// 回 16，因为这个大小在编译器就能确定，不会再更改了，所以 unsafe.Sizeof 的结果其实可以直接赋值给 const 常量。学过 C 语言的要特别
// 注意，此 sizeof 不是 C 的那个 sizeof。
func CompileSizedof() {
	var str string = "english 中文"
	const byteInSize = unsafe.Sizeof(str)
	// 不管 str 是什么内容 byteInSize 都是 16
	// 字符串在 go 内部用 2 个字段表示(一个地址值和一个长度值，都占用 8 个字节)
	fmt.Println(byteInSize)
}

// Go 语言里经常把常量当做枚举来定义
const (
	apple  = 0
	banana = 1
	orange = 2
)

// 不过这样 0 1 2 给维护增加了负担，go 语言定义了一个特殊的常量叫 iota，编译的时候会被编译器修改，它的作用是在 const 第一行被设置成 0，
// 每增加一行，它的值就会累加一次，就像是 const 的索引。所以下面的定义还是 0 1 2:
// const (
// 	apple = iota
// 	banana = iota
// 	orange = iota
// )

// PrintAddr 如果把这个内存地址赋值给另外一个变量，这个变量就是指针变量，表示它是指向那个内存地址。怎么获得一个变量的地址呢? 使用 & 操作符，用 %p 来格式化。
func PrintAddr() {
	sore := 10
	fmt.Printf("%p", &sore)
}

// HoldVarAddr 持有变量地址的指针变量可以通过 * 操作符读取它地址里存放的值，并且修改它。
func HoldVarAddr() {
	score := 10
	addr := &score
	// 相当于C语言里面的解引用
	*addr = 20
	// 打印 20
	fmt.Println(score)
}

// 不能修改入参 score 参数值
// 函数调用的时候参数被拷贝
func cannotUpdateScore(score int) {
	// 0xc00001c138
	fmt.Println(&score)
	score++
	// 11
	fmt.Println(score)
}

// IncScoreFailed 调高分数
// 函数调用的时候，默认参数是按照值传递的，对于普通的变量类型，传递的参数会被拷贝一份压入堆栈，这种情况下函数体内的代码不会影
// 响到传入的参数本身，因为他们的内存地址并不是同一个位置。所以下面的代码无法修改 score 的值。
func IncScoreFailed() {
	score := 10
	// 0xc00001c130
	fmt.Println(&score)
	cannotUpdateScore(score)
	// 10
	fmt.Println(score)
}

// 可以修改入参 score 参数值
// 函数调用的时候参数被拷贝
func canUpdateScore(ptrScore *int) {
	// 已经是地址了 0xc00001c140
	fmt.Println(ptrScore)
	*ptrScore++
	// 11
	fmt.Println(*ptrScore)
}

// IncScoreSucceeded 调高分数
func IncScoreSucceeded() {
	score := 10
	// 0xc00001c140
	fmt.Println(&score)
	canUpdateScore(&score)
	// 11
	fmt.Println(score)
}
