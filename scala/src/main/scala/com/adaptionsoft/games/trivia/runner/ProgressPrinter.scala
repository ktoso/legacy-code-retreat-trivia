package com.adaptionsoft.games.trivia.runner

class ProgressPrinter(total: Long) {
  var counter = 0L
  var lastPercent = 0L

  def ++() {
    counter += 1
    val currentPercent: Long = counter * 100L / total
    if (currentPercent != lastPercent) {
      print( "\r" + currentPercent + "% (" + counter + "/" + total + ")")
      lastPercent = currentPercent
    }
  }
}