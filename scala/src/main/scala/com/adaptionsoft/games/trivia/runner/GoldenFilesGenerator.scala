package com.adaptionsoft.games.trivia.runner

import collection.mutable.ListBuffer
import com.google.common.io.Files
import collection.JavaConversions._
import java.io.{PrintWriter, BufferedOutputStream, FileOutputStream, File}
import com.google.common.base.Charsets
import pl.project13.scala.rainbow._

object GoldenFilesGenerator extends App {

  val Max = 10000
  val seeds = 1 to Max

  val progress = new ProgressPrinter(Max)

  seeds foreach { seed =>
    val out = new ListBuffer[String]

    val printlnToOut: (String) => Unit = (s) => out += s

    val game = new GameRunner(println = printlnToOut) {
      override lazy val rand = new java.util.Random(seed)
    }

    game.main(Array())

    val goldenFile = new File("golden/seed_%s.txt".format(seed))
    Files.createParentDirs(goldenFile)

    val writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(goldenFile)))
    writer.write(out.mkString("\n"))
    writer.close()

    progress++
  }

}
