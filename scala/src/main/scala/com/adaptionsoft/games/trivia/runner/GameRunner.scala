package com.adaptionsoft.games.trivia.runner

import com.adaptionsoft.games.uglytrivia.Game

object GameRunner extends GameRunner(println = Predef.println(_))

class GameRunner(println: (String) => Unit) {

  var notAWinner = false

  lazy val rand = new java.util.Random

  def main(args: Array[String]) {
    var aGame = new Game(println = println)
    aGame.add("Chet")
    aGame.add("Pat ")
    aGame.add("Sue")

    do {
      aGame.roll(rand.nextInt(5) + 1)
      if (rand.nextInt(9) == 7) {
        notAWinner = aGame.wrongAnswer
      }
      else {
        notAWinner = aGame.wasCorrectlyAnswered
      }
    } while (notAWinner)
  }
}