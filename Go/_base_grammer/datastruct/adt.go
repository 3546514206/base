package datastruct

import (
	"fmt"
	"time"
)

type MyInt int

type Book struct {
	Name    string
	Author  string
	Publish time.Time
}

func changeBookNameFailed(book Book) {
	book.Name = "无字天书"
	fmt.Printf("%p\n", &book)
	fmt.Println(book)
}

func changeBookNameSuccessed(ptrBook *Book) {
	ptrBook.Name = "无字天书"
	fmt.Printf("%p\n", ptrBook)
	fmt.Println(*ptrBook)
}
