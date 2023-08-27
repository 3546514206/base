package main

import (
	"fmt"
	"os"
	"runtime/trace"
)

func doSomeThing() {
	fmt.Println("world")
}

func main() {

	f, err := os.Create("trace.out")
	if err != nil {
		panic(err)
	}

	defer f.Close()

	err = trace.Start(f)

	if err != nil {
		panic(err)
	}

	defer trace.Stop()

	go doSomeThing()
	fmt.Println("hello")
}
