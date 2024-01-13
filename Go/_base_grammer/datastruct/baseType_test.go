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

// SliceHeader is the runtime representation of a slice.
// It cannot be used safely or portably and its representation may
// change in a later release.
// Moreover, the Data field is not sufficient to guarantee the data
// it references will not be garbage collected, so programs must keep
// a separate, correctly typed pointer to the underlying data.
// type SliceHeader struct {
// 	Data uintptr
// 	Len  int
// 	Cap  int
// }
// go 把数组作为底层的数据结构作为引用，定了切片类型，也就说切片是对数组一个连续片段的引用，可以是整个数组，也可以是子集。之前我们提到
// 过 StringHeader 字符串的结构，字符串就是一个特殊的数组，而切片的结构定义其实比 StringHeader 多了一个 cap 字段。
func Test_sliceSize(t *testing.T) {
	sliceSize()
}
