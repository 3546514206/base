package main

type AudioPlayer struct {
	Name string
}

func (ap AudioPlayer) Recorde() string {
	//TODO implement me
	panic("implement me")
}

func (ap AudioPlayer) Play() string {
	return ap.Name + " is playing audio"
}

type VideoPlayer struct {
	Name string
}

func (vp VideoPlayer) Recorde() string {
	//TODO implement me
	panic("implement me")
}

func (vp VideoPlayer) Play() string {
	return vp.Name + " is playing video"
}

type AudioRecorder struct {
	Name string
}

func (ar AudioRecorder) Play() string {
	//TODO implement me
	panic("implement me")
}

func (ar AudioRecorder) Recorde() string {
	//TODO implement me
	panic("implement me")
}

func (ar AudioRecorder) Record() string {
	return ar.Name + " is recording audio"
}

type VideoRecorder struct {
	Name string
}

func (vr VideoRecorder) Play() string {
	//TODO implement me
	panic("implement me")
}

func (vr VideoRecorder) Recorde() string {
	//TODO implement me
	panic("implement me")
}

func (vr VideoRecorder) Record() string {
	return vr.Name + " is recording video"
}
