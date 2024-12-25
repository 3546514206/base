package main

import "fmt"

type HookFunc func() string

var hooks []HookFunc

// 注册钩子函数
func RegisterHook(hook HookFunc) {
	hooks = append(hooks, hook)
}

func RunHooks() []string {

	var results []string
	// 执行函数
	for index, hook := range hooks {
		fmt.Printf("执行第%d个钩子函数\n", index)
		rz := hook()
		results = append(results, rz)
	}
	// 返回结果
	return results
}

func main() {
	// 注册两个钩子函数
	RegisterHook(func() string {
		return "Hook 1 executed"
	})
	RegisterHook(func() string {
		return "Hook 2 executed"
	})

	results := RunHooks()

	for index, rs := range results {
		fmt.Printf("第%d个结果：%s\n", index, rs)
	}
}
