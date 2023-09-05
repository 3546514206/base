package main

import "fmt"

type Phone interface {
	call()
}

type NokiaPhone struct {
}

func (nokiaPhone NokiaPhone) call() {
	fmt.Println("i am nokia, i can call you")
}

type ApplePhone struct {
}

func (applePhone ApplePhone) call() {
	fmt.Println("i am apple, i can call you")
}

func main() {
	var phone Phone
}
