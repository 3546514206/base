package demo

import (
	"_base_grammer/stract/demo/duck"
	"_base_grammer/stract/demo/move"
	"_base_grammer/stract/demo/person"
	"testing"
)

func hurryUp(movable move.Movable, distance int) int {
	return movable.Walk(distance/2) + movable.Swim(distance/2)
}

func TestName(t *testing.T) {
	d := &duck.Duck{Name: "kity", WalkSpeed: 10, SwimSpeed: 20}
	p := &person.Person{Name: "hangman", WalkSpeed: 20, SwimSpeed: 10}
	hurryUp(d, 2000)
	hurryUp(p, 2000)
}
