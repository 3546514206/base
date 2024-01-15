package utest

import "fmt"

func incUpdateScore(ptrScore *int, increment int) {
	*ptrScore += increment
}

func CreateSlice() {
	s8 := []int{4: 5}
	// [0 0 0 0 5]
	fmt.Println(s8)
	// 5 5
	fmt.Println(len(s8), cap(s8))

	const size = 5
	const max = 10
	s9 := make([]int, size, max)
	// [0 0 0 0 0]
	fmt.Println(s9)
	// 5 10
	fmt.Println(len(s9), cap(s9))

	s10 := new([]int)
	*s10 = append(*s10, 1, 2, 3)
	// [1 2 3]
	fmt.Println(*s10)
	// 3 4
	fmt.Println(len(*s10), cap(*s10))
}
