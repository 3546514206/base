package main

import (
	"fmt"
	"strconv"
)

func main() {
	var testStr = "123"

	result, error := strconv.Atoi(testStr)
	if error != nil {
		fmt.Println("convert error")
	} else {
		fmt.Println(result + 1)
	}

}
