package io.mattmoore.scala.compiler.plugins

object DivisionByZero extends App {
  val three = 3
  val amount = three / 0
  println(amount)
}
