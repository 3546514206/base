package main

import "fmt"

func main() {
	audioDevice := AudioPlayer{Name: "AudioDevice"}
	videoDevice := VideoPlayer{Name: "VideoDevice"}
	audioRecordingDevice := AudioRecorder{Name: "AudioRecordingDevice"}
	videoRecordingDevice := VideoRecorder{Name: "VideoRecordingDevice"}

	devices := []MutiMediaDevice{audioDevice, videoDevice, audioRecordingDevice, videoRecordingDevice}

	for _, device := range devices {
		fmt.Println(device.Play())
	}
}
