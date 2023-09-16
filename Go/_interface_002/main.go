package main

import "fmt"

type People interface {
	Say(str string) string
}

type Student struct {
}

func (s Student) Say(str string) string {
	talk := ""
	if str == "OK" {
		talk = "Hi"
	} else {
		talk = "Bye"
	}

	return talk
}

func main() {

	var people People
	people = Student{}

	str := "love"
	fmt.Println(people.Say(str))
}
