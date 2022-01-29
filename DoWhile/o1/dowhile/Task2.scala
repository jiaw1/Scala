package o1.dowhile

// This task is part of Chapter 8.3.

import scala.io.StdIn._

// Example run (with user inputs in *asterisks*:
//
// I will compute the squares of positive integers and discard other numbers.
// To stop, just hit Enter.
// Please enter the first number: *10*
// Its square is: 100
// Another number: *0*
// Another number: *-1*
// Another number: *20*
// Its square is: 400
// Another number: *30*
// Its square is: 900
// Another number: *0*
// Another number: *40*
// Its square is: 1600
// Another number:
// Done.
// Number of discarded inputs: 3

object Task2 extends App {
  println("I will compute the squares of positive integers and discard other numbers.")
  println("To stop, just hit Enter.")

  var discardCount = 0
  var inputString = readLine("Please enter the first number: ")
  while (inputString.nonEmpty) {
    inputString.toIntOption match {
      case Some(number) if number > 0 =>
        println("Its square is: " + number * number)
      case nonPositiveOrInvalid =>
        discardCount += 1
    }
    inputString = readLine("Another number: ")
  }

  println("Done.")
  println("Number of discarded inputs: " + discardCount)
}

