package stract

import (
	"_base_grammer/stract/duck"
	"_base_grammer/stract/move"
	"_base_grammer/stract/person"
)

func hurryUp(movable move.Movable, distance int) int {
	return movable.Walk(distance/2) + movable.Swim(distance/2)
}

func main() {
	d := &duck.Duck{Name: "kity", WalkSpeed: 10, SwimSpeed: 20}
	p := &person.Person{Name: "hangman", WalkSpeed: 20, SwimSpeed: 10}
	hurryUp(d, 2000)
	hurryUp(p, 2000)
}
