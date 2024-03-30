package main

import (
	"fmt"
	"github.com/xuri/excelize/v2"
)

func main() {
	file, err := excelize.OpenFile("1.xlsx")
	if err != nil {
		fmt.Println(err)
		return
	}

	cells, _ := file.GetRows("广东93+")
	for _, row := range cells {
		for _, colCell := range row {
			fmt.Print(colCell, "\t")
		}
		fmt.Println()
	}
}
