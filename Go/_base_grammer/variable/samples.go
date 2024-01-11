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
	str := "english 中文"
	const byteInSize = unsafe.Sizeof(str)
	// 不管 str 是什么内容 byteInSize 都是 16
	// 字符串在 go 内部用 2 个字段表示(一个地址值和一个长度值，都占用 8 个字节)
	fmt.Println(byteInSize)
}
