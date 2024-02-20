package person

// Person 人
type Person struct {
	Name      string
	Age       int
	WalkSpeed int
	SwimSpeed int
}

func (p Person) Walk(distance int) int {
	return distance
}

func (p Person) Swim(distance int) int {
	return distance
}
