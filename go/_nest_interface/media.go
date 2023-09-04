package _nest_interface

type Player interface {
	Play() string
}

type Recorder interface {
	Recorde() string
}

type MutiMediaDevice interface {
	Player
	Recorder
}
