package main

import (
	"math"
)

type Point struct{ X, Y float64 }

func (p Point) Distance1(q *Point) float64 {
	return math.Hypot(q.X-p.X, q.Y-p.Y)
}

func (p Point) Distance2(q Point) float64 {
	return math.Hypot(q.X-p.X, q.Y-p.Y)
}

func main() {

	p := Point{1, 2}
	q := Point{4, 6}

	p.Distance1(&q)
	p.Distance2(q)

	func1 := Point.Distance2
	func2 := (*Point).Distance2

	func1(p, q)
	func2(&p, q)
}
