package o1.dowhile

// This task is part of Chapter 8.3.

import scala.io.StdIn._

object Task1 extends App {
  def report(input: String) = "The input is " + input.length + " characters long."
  var newestInput = readLine("Enter some text: ")
  while (newestInput != "please") {
    println(report(newestInput))
    newestInput = readLine("Enter some text: ")
  }
}

