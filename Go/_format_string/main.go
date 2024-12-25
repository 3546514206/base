package main

import "fmt"

func main() {
	var stockCode = 123
	var endDate = "2010-12-31"
	var url = "Code=%d endDate=%s"
	var targetUrl = fmt.Sprintf(url, stockCode, endDate)
	fmt.Println(targetUrl)
}
