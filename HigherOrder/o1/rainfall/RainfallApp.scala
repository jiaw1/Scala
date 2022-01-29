package o1.rainfall

import scala.io.StdIn._

object RainfallApp1 extends App {

  def inputs = LazyList.continually( readLine("Enter rainfall (or 999999 to stop): ").toInt )
  val measurements = inputs.takeWhile( _ != 999999 ).filter( _ >= 0 )

  if (measurements.nonEmpty) {
    val average = measurements.sum / measurements.size
    println(s"The average is $average.")
  } else {
    println("No valid data. Cannot compute average.")
  }

}



// One of many possible alternative implementations (with more named abstractions):
object RainfallApp2 extends App {

  val Sentinel = 999999

  def readInput() = {
    readLine(s"Enter rainfall (or $Sentinel to stop): ").toInt
  }

  def isMeasurement(input: Int) = input != Sentinel
  def isValid(measurement: Int) = measurement >= 0
  def average(numbers: LazyList[Int]) = if (numbers.isEmpty) None else Some(numbers.sum / numbers.size)
  def measurements = LazyList.continually( readInput() ).takeWhile(isMeasurement).filter(isValid)

  val output = average(measurements) match {
    case Some(result) => s"The average is $result."
    case None         => "No valid data. Cannot compute average."
  }
  println(output)

}


// Like the first solution above, but uses toIntOption and flatten
// to discard non-integer inputs (rather than crashing).
object RainfallApp extends App {

  def inputs = LazyList.continually( readLine("Enter rainfall (or 999999 to stop): ").toIntOption )
  val measurements = inputs.flatten.takeWhile( _ != 999999 ).filter( _ >= 0 )

  if (measurements.nonEmpty) {
    val average = measurements.sum / measurements.size
    println(s"The average is $average.")
  } else {
    println("No valid data. Cannot compute average.")
  }

}

