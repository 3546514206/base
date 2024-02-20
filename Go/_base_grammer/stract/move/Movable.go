package move

type Movable interface {
	Walk(distance int) int

	Swim(distance int) int
}
