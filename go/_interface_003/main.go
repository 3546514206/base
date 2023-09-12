package main

import "fmt"

type People interface {
	Show()
}

type Boy interface {
	Eat()
}

type Man interface {
	People
	Boy
}

type Student struct {
}

func (stu *Student) Show() {
	fmt.Println("testShow")
}

func (stu *Student) Eat() {
	fmt.Println("testEat")
}

func live() People {
	var stu *Student
	return stu
}

func main() {
	
	var stu Man
	stu = new(Student)
	stu.Show()
	
	//var boy Boy
	//var people People
	//boy = new(Student)
	//boy.Show()
	//people = boy
	//people.Show()
	//
	//if live() == nil {
	//	fmt.Println("AAAAAA")
	//} else {
	//	fmt.Println("BBBBBB")
	//}
}
