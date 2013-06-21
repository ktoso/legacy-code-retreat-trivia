package main

import (
	"math/rand"
	"flag"
	"fmt"
)

func main() {
	notAWinner := false

	game := NewGame()

	game.Add("Chet")
	game.Add("Pat")
	game.Add("Sue")

	// hardcoding seed
	seed := flag.Int64("seed", 1, "seed number to use for random number generator")
	flag.Parse()
	rand.Seed(*seed)

	for {
		game.Roll(rand.Intn(5) + 1)

		if rand.Intn(9) == 7 {
			notAWinner = game.WrongAnswer()
		} else {
			notAWinner = game.WasCorrectlyAnswered()

		}

		if !notAWinner {
			break
		}
	}

	fmt.Println("seed", *seed)
}
