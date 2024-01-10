package _type

import (
	"fmt"
	"unsafe"
)

func ShowAutoType() {
	// Go 语言可以根据数据的值自动推导变量的类型
	str := "hello world"

	// %T 用来显示 str 的类型，结果显示 string (\n 表示打印换行符)
	fmt.Printf("%T\n", str)
	fmt.Println(str)
}

// ShowStaticType 我们也可以明确的去表明一个数据的类型
func ShowStaticType() {
	// 明确的定义 str 是一个字符串类型，具有一个默认的空值(空字符串)
	var str string

	// 也可以申明变量同时赋初值
	// var str string = "world"
	str = "hello world"
	fmt.Println(str)
}

// PrintSpace 字符串类型是字符的集合，字符类型使用两个单号 '' 表示，在 go 语言中字符类型是 uint8，
// 也叫 byte 字节类型，用于表示 ASCII 码表里的字符，如下代码的作用是打印一个空白字符:
func PrintSpace() {
	var space byte
	space = 32
	// 打印 (一个空格，看不见，因为在 ASCII 码表里空格的数值是 32，%c 表明把 space 变量当做字符来打印)
	fmt.Printf("%c\n", space)
	// 打印 uint8,原来 go 语言使用关键字 type 把 uint8 这个 8 位的无符号整型定义成了 byte，
	// type byte = uint8
	// byte 就是个昵称(别名)，也就说 byte 只是写代码的时候叫 byte，到编译阶段其实都是 uint8 了
	fmt.Printf("%T\n", space)
	// %d 表示期望打印整数，显示结果是 1
	fmt.Printf("%d", unsafe.Sizeof(space))
}

// PrintChinese 打印 Unicode 编码字符
func PrintChinese() {
	// 超过 byte 的大小
	// var a byte = '中'
	var x rune = '中'
	var y rune = '国'
	fmt.Printf("%c%c\n", x, y)
}

// NumberOverflow 因为 50 的二进制是 110010，110010 和 110010 的乘积(按位相乘再相加)是 100111000100 = 2500，但是 num3 只有 8 位，
// 所以截取末尾的 8 位后结果是 11000100 = 196。所以要选择合适的类型，太大了可能造成浪费给内存分配和回收造成压力，太小了可能可能会造
// 成 bug 产生意料之外的结果。
func NumberOverflow() {
	// 最大表是 256
	var num1 uint8 = 50
	// 最大表是 256
	var num2 uint8 = 50
	var num3 uint8

	num3 = num1 * num2
	// 期望是 2500，结果显示 196，改为 uint16 则正常
	fmt.Printf("%d", num3)
}
