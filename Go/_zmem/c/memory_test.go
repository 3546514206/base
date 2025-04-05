package c

import (
	//"../c"
	"fmt"
	"testing"
)

func TestMemorC(t *testing.T) {
	data := Malloc(4)
	//fmt.Printf("data % + v,%T", data, data)
	mydata := (*uint32)(data)
	fmt.Println(mydata)
	Free(data)
}
