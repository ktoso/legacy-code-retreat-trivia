package com.adaptionsoft.games.trivia.runner

import collection.mutable.ListBuffer
import com.google.common.io.Files
import java.io.File
import com.google.common.base.Charsets
import sys.process._
import collection.JavaConversions._
import pl.project13.scala.rainbow._

object GoldenTester extends App {

  val RedDot = ".".red
  val GreenDot = ".".green

  val Max = 10000
  val seeds = 1 to Max

  seeds foreach { seed =>
    val out = new ListBuffer[String]

    val printlnToOut: (String) => Unit = (s) => out += s

    val game = new GameRunner(println = printlnToOut) {
      override lazy val rand = new java.util.Random(seed)
    }

    game.main(Array())

    outputMatchesGolden(seed, out) match {
      case true => print(GreenDot)
      case false => print(RedDot)
    }
  }

  def outputMatchesGolden(seed: Int, current: ListBuffer[String]): Boolean = {
    val golden = Files.readLines(new File("golden/seed_%s.txt".format(seed)), Charsets.UTF_8)

    (golden zip current) foreach { case (goldenLine, currentLine) =>
      if (goldenLine != currentLine) {
        println("seed %d: first error: expected '%s' but was '%s'".format(seed, goldenLine, currentLine))
        return false
      }
    }

    true
  }

}
