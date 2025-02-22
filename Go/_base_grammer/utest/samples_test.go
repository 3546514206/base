package utest

import "testing"

func Test_incUpdateScore(t *testing.T) {
	checkEqual := func(t *testing.T, expected, got int) {
		t.Helper()
		if expected != got {
			t.Errorf("有问题, 期望 %d, 实际是 %d", expected, got)
		}
	}

	t.Run("should increment score by + number", func(t *testing.T) {
		score := 10
		incUpdateScore(&score, 1)
		checkEqual(t, 11, score)
	})

	t.Run("should increment score by - number", func(t *testing.T) {
		score := 10
		incUpdateScore(&score, -1)
		checkEqual(t, 9, score)
	})
}
