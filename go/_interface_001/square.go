package main

type Square struct {
	side float64
}

func (s Square) Area() float64 {
	return s.side * s.side
}

func (s Square) setValue(value float64) {
	s.side = value
}
