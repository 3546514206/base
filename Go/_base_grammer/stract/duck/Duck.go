package duck

// Duck 鸭子
type Duck struct {
	Category  string
	Name      string
	WalkSpeed int
	SwimSpeed int
}

func (d Duck) Walk(distance int) int {
	return distance
}

func (d Duck) Swim(distance int) int {
	return distance
}
