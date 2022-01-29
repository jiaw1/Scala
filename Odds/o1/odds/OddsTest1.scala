package o1.odds

// This program is introduced in Chapter 2.7.
// It creates two Odds objects and calls various methods.

import scala.io.StdIn._

object OddsTest1 extends App {

  println("Please enter the odds of an event as two integers on separate lines.")
  println("For instance, to enter the odds 5/1 (one in six chance of happening), write 5 and 1 on separate lines.")
  val firstInput = readInt()
  val secondInput = readInt()
  val odds = new Odds(firstInput, secondInput)

  println("The odds you entered are:")
  println("In fractional format: "    + odds.fractional)
  println("In decimal format: "       + odds.decimal)
  println("Event probability: "       + odds.probability)
  println("Reverse odds: "            + odds.not)
  println("Odds of happening twice: " + odds.both(odds))

  println("Please enter the size of a bet:")
  val thirdInput = readDouble()
  println("If successful, the bettor would claim " + odds.winnings(thirdInput))

  println("Please enter the odds of a second event as two integers on separate lines.")
  val fourthInput = readInt()
  val fifthInput = readInt()
  val secondOdds = new Odds(fourthInput, fifthInput)

  println("The odds of both events happening are: " + odds.both(secondOdds))
  println("The odds of one or both happening are: " + odds.either(secondOdds))
}




//Toinen tapa

package o1.odds

// This variant of OddsTest1 features the requestOdds method.

import scala.io.StdIn._

object OddsTest1_RO extends App {

  def requestOdds() = {
    val first = readInt()
    val second = readInt()
    new Odds(first, second)
  }

  println("Please enter the odds of an event as two integers on separate lines.")
  println("For instance, to enter the odds 5/1 (one in six chance of happening), write 5 and 1 on separate lines.")
  val odds = requestOdds()

  println("The odds you entered are:")
  println("In fractional format: "    + odds.fractional)
  println("In decimal format: "       + odds.decimal)
  println("Event probability: "       + odds.probability)
  println("Reverse odds: "            + odds.not)
  println("Odds of happening twice: " + odds.both(odds))

  println("Please enter the size of a bet:")
  val thirdInput = readDouble()
  println("If successful, the bettor would claim " + odds.winnings(thirdInput))

  println("Please enter the odds of a second event as two integers on separate lines.")
  val secondOdds = requestOdds()

  println("The odds of both events happening are: " + odds.both(secondOdds))
  println("The odds of one or both happening are: " + odds.either(secondOdds))
}




