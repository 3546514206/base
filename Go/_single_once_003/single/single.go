package single

import (
	"fmt"
	"sync"
)

type singleObject struct {
	ip string
}

var single *singleObject

var once = sync.Once{}

func (o singleObject) PrintIpInfo() {
	fmt.Print(o.ip)
}

func GetSingleObj() *singleObject {

	// sync.Once 机制：
	// sync.Once 是 Go 语言标准库 sync 包中的一种同步机制，用于确保某个函数只会被执行一次。
	// sync.Once 可以用于在任何地方，不仅仅是包级别的初始化，以确保某个操作在多次调用中只执行一次。
	// sync.Once 使用一个 Do 方法，传递一个需要执行的函数，它会确保该函数只会被执行一次，即使在多个并发调用中也是如此。
	once.Do(func() {
		single = &singleObject{
			ip: "192.1.1.1",
		}
	})

	return single
}
