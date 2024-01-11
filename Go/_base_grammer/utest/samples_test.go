package utest

import "testing"

func Test_incUpdateScore(t *testing.T) {
	score := 10
	expected := 14
	incUpdateScore(&score, 3)

	if score != expected {
		t.Errorf("有问题, 期望 %d, 实际是 %d", expected, score)
	}
}
