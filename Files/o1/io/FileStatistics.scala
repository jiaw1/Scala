package o1.io

import scala.io.Source
import scala.io.StdIn._
import scala.math.Ordering.Double.TotalOrdering

// This program is introduced in Chapter 11.3.

object FileStatistics extends App {

  val fileName = readLine("Please enter the name of the input file: ")

  val moduleFolderName = "Files/"
  val file = Source.fromFile(moduleFolderName + fileName)

  try {
    val numbers = file.getLines.toVector.map( _.toDouble )
    println("Count: " + numbers.size)
    println("Average: " + average(numbers))
    println("Median: " + median(numbers))
    println("Highest number of occurrences: " + maxOccurrences(numbers))
  } finally {
    file.close()
  }


  def average(numbers: Vector[Double]) = numbers.sum / numbers.size

  def median(numbers: Vector[Double]) = {
    val halfway = numbers.size / 2
    val ascending = numbers.sorted
    if (numbers.size % 2 == 1)
      ascending(halfway)
    else
      (ascending(halfway - 1) + ascending(halfway)) / 2
  }

  def maxOccurrences(numbers: Vector[Double]) = numbers.groupBy(identity).values.map( _.size ).max

}