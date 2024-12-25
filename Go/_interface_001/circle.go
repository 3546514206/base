package main

// 定义结构体
type Circle struct {
	radius float64
}

func (c Circle) Area() float64 {
	return 3.14 * c.radius * c.radius
}

func (c Circle) setValue(value float64) {
	c.radius = value
}
