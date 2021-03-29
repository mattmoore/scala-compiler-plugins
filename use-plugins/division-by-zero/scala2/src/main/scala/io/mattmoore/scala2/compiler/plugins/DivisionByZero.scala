package io.mattmoore.scala2.compiler.plugins

object DivisionByZero extends App {
  val two = 2
  val amount = two / 0
  println(amount)
}
