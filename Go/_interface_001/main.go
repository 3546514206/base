package main

import "fmt"

func main() {
	circle := Circle{radius: 5}
	square := Square{side: 8}

	var shape Shape
	shape = circle
	fmt.Printf("Circle Area: %2.f\n", shape.Area())

	shape = square
	fmt.Printf("Square Area: %2.f\n", shape.Area())

	shape.setValue(9)
	fmt.Printf("Square Area: %2.f\n", shape.Area())
}
