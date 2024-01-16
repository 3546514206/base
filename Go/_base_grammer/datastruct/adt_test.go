package datastruct

import (
	"fmt"
	"testing"
	"time"
)

func Test_adt(t *testing.T) {
	book := Book{
		"算法导论", "大神", time.Now(),
	}
	fmt.Printf("%p\n", &book)

	changeBookNameFailed(book)
	// 算法导论
	fmt.Println(book)

	changeBookNameSuccessed(&book)
	// 无字天书
	fmt.Println(book)
}
