package datastruct

import (
	"fmt"
	"reflect"
	"testing"
)

func updateFailed(arr [3]int) {

	fmt.Println(arr)
	for index, _ := range arr {
		arr[index] = -1
	}
	fmt.Println(arr)
}

// go 的数组是一个值类型，把一个数组直接传递给函数，将发生数据拷贝，函数内对参数数组的改变不会影响到原数组。
func Test_updateFailed(t *testing.T) {
	var arr [3]int = [3]int{1, 2, 3}
	fmt.Println(arr)
	updateFailed(arr)
	// 没有改变数组的值
	fmt.Println(arr)
}

func updateSuccessed(ptrArr *[3]int) {
	fmt.Printf("%p\n", ptrArr) // 0xc0000cc140
	ptrArr[0] = 1
	ptrArr[1] = 1
	ptrArr[2] = 1
	fmt.Println(*ptrArr) // [1 1 1]
}

// 和所有的值类型一样，要修改数组的值，需要传递指针。
func Test_updateSuccessed(t *testing.T) {
	arr := [3]int{1, 2, 3}
	fmt.Printf("%p\n", &arr) // 0xc0000cc140
	updateSuccessed(&arr)
	fmt.Println(arr) // [1 1 1]
	if !reflect.DeepEqual(arr, [3]int{1, 1, 1}) {
		t.Fatal("函数改变了原数组")
	}
}
