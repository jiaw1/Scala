package o1.football3

import scala.collection.mutable.Buffer

/** The class `Season` represents a season in a football league, that is, a series
  * of matches between various clubs. The methods in the class allow adding matches
  * to a season as well as calculating some very basic season statistics (the total
  * number of matches, the biggest win during the season, etc.). */
class Season {

  private val matches = Buffer[Match]()                         // container: all matches of the season (so far)
  private var matchWithBiggestMargin: Option[Match] = None      // most-wanted holder: the match with the highest win margin (so far)


  /** Adds a match result to the season. The match is assumed to have finished,
    * so no further goals will be added to it later. */
  def addResult(newResult: Match) = {
    // Other implementations are certainly possible. Higher-order functions and loops
    // have been purposely avoided here since they haven't been covered just yet.
    this.matches += newResult
    val newBiggest = this.matchWithBiggestMargin match {
      case Some(oldBiggest) => this.chooseBigger(newResult, oldBiggest)
      case None             => newResult
    }
    this.matchWithBiggestMargin = Some(newBiggest)
  }


  private def chooseBigger(m1: Match, m2: Match) = if (m1.goalDifference.abs > m2.goalDifference.abs) m1 else m2


  /** Returns the number of matches played in the season so far. */
  def numberOfMatches = this.matches.size


  /** Returns a match played in this season. The match to be returned is identified by
    * the given match number parameter: 0 means the first match of the season, 1 the
    * second, 2 the third, and so on. The match is wrapped in an `Option`, and `None`
    * is returned if there is no match with that number. */
  def matchNumber(number: Int) = this.matches.lift(number)
  // Another implementation, which does not use the lift method introduced in Chapter 4.3:
  // if (number >= 0 && number < this.numberOfMatches) Some(this.matches(number)) else None


 /** Returns the match most recently added to this season. The match is wrapped in an
   *  `Option`, and `None` is returned if no matches have been played yet. */
  def latestMatch = this.matchNumber(this.numberOfMatches - 1)
  // There is also a method lastOption which could be used here: this.matches.lastOption


  /** Returns the match whose margin of victory was higher than that of any other match
    * in the season. If there is a tie for the biggest win, returns the match that was
    * added to the season first.
    *
    * If all of the matches have a win margin of zero (all are tied), returns the first
    * of all the matches.
    *
    * The result is wrapped in an `Option`; `None is returned if no matches have been played yet. */
  def biggestWin = this.matchWithBiggestMargin   // loops and higher-order functions avoided here, too, because not covered yet


}

