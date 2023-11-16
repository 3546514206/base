package main

import "fmt"

type Books struct {
	title   string
	author  string
	subject string
	bookId  int
}

func main() {
	/* 声明 Book1 为 Books 类型 */
	var Book1 Books
	/* 声明 Book2 为 Books 类型 */
	var Book2 Books

	/* book 1 描述 */
	Book1.title = "Go 语言"
	Book1.author = "www.w3cschool.cn"
	Book1.subject = "Go 语言教程"
	Book1.bookId = 6495407

	/* book 2 描述 */
	Book2.title = "Python 教程"
	Book2.author = "www.w3cschool.cn"
	Book2.subject = "Python 语言教程"
	Book2.bookId = 6495700

	/* 打印 Book1 信息 */
	fmt.Printf("Book 1 title : %s\n", Book1.title)
	fmt.Printf("Book 1 author : %s\n", Book1.author)
	fmt.Printf("Book 1 subject : %s\n", Book1.subject)
	fmt.Printf("Book 1 book_id : %d\n", Book1.bookId)

	/* 打印 Book2 信息 */
	fmt.Printf("Book 2 title : %s\n", Book2.title)
	fmt.Printf("Book 2 author : %s\n", Book2.author)
	fmt.Printf("Book 2 subject : %s\n", Book2.subject)
	fmt.Printf("Book 2 book_id : %d\n", Book2.bookId)
}
