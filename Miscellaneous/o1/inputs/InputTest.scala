package o1.inputs

import io.StdIn._

/** A tiny app for experimenting with user input. Reads in a line of input
  * and uses it both as a string and as a number. */
object InputTest extends App {
  val input = readLine("Please enter an integer: ")
  val digits = input.length
  input.toIntOption match {
    case Some(number) =>
      println(s"Your number is $digits digits long.")
      println(s"Multiplying it by its length gives ${number * digits}.")
    case None =>
      println("That is not a valid input. Sorry!")
  }

}

