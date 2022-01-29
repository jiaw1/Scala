package o1.football1

/** The class `Match` represents match results in a football match statistics program.
  * A match is played between teams from two clubs: a home club and an away club.
  * Goals scored by either team can be added to the match object.
  *
  * The class is expected to be used so that a match object with no goals is initially
  * created as a real-life match starts. Goals are added incrementally as the match
  * progresses. (A match object has mutable state.)
  *
  * @param home  the club whose team plays at home in the match
  * @param away  the club whose team plays away in the match    */
class Match(val home: Club, val away: Club) {

  private var homeCount = 0    // stepper: starts at zero and increases as goals are scored
  private var awayCount = 0    // stepper: starts at zero and increases as goals are scored


  /** Records a goal as having been scored in the match by the home team. */
  def addHomeGoal() = {
    this.homeCount = this.homeCount + 1
  }


  /** Records a goal as having been scored in the match by the away team. */
  def addAwayGoal() = {
    this.awayCount = this.awayCount + 1
  }


  /** Returns the number of goals that have been scored (so far) by the home team. */
  def homeGoals = this.homeCount


  /** Returns the number of goals that have been scored (so far) by the away team. */
  def awayGoals = this.awayCount


  /** Returns the total number of goals scored by the two teams. */
  def totalGoals = this.homeGoals + this.awayGoals


  /** Determines whether this match is entirely goalless, that is, whether neither
    * team has scored a single goal. */
  def isGoalless = this.totalGoals == 0


  /** Returns a boolean value indicating whether the home team won (or would win
    * if the match ended with the current score). Tied scores produce `false`. */
  def isHomeWin = this.homeGoals > this.awayGoals


  /** Returns a boolean value indicating whether the away team won (or would win
    * if the match ended with the current score). Tied scores produce `false`. */
  def isAwayWin = this.homeGoals < this.awayGoals


  /** Returns a boolean value indicating whether the game ended in a draw (or
    * would do so if the match ended with the current score). */
  def isTied = this.homeGoals == this.awayGoals


  /** Returns the goal difference of the match. The sign of the number indicates
    * which team scored more goals.
    * @return the goal difference as a positive number if the home team won, a
    *         negative number if the away team won, or zero in case of a tied match */
  def goalDifference = this.homeGoals - this.awayGoals


  /** Determines whether this match has a higher total score than another given match.
    * @return `true` if more goals were scored in total in this match than in the given match, `false` otherwise */
  def isHigherScoringThan(anotherMatch: Match) = this.totalGoals > anotherMatch.totalGoals


  /** Returns the name of the stadium where the match is played. That is, returns
    * the name of the home team's stadium. */
  def location = this.home.stadium


  /** Produces a textual description of the match's current state, in a format
    * illustrated by the examples below. In these examples "Liverpool" is the name
    * of the home team, "Everton" the name of the away team, and "Anfield" the name
    * of the home team's stadium.
    *
    * Goalless draw (no goals for either side):
    * {{{
    * Liverpool vs. Everton at Anfield: tied at nil-nil
    * }}}
    *
    * Other tied score (e.g., 2-2):
    * {{{
    * Liverpool vs. Everton at Anfield: tied at 2-all
    * }}}
    *
    * Home score higher (e.g., 4-0):
    * {{{
    * Liverpool vs. Everton at Anfield: 4-0 to Liverpool
    * }}}
    *
    * Away score higher (e.g., 2-4):
    * {{{
    * Liverpool vs. Everton at Anfield: 4-2 to Everton
    * }}}
    *
    * Note that although the name of the home team is always listed first, but the
    * leader's score is shown first, even if the away team is leading. */
  override def toString = {
    val heading = s"$home vs. $away at $location: "
    val gameState =
      if (this.isGoalless)
        "tied at nil-nil"
      else if (this.isTied)
        s"tied at $homeGoals-all"
      else if (this.isHomeWin)
        s"$homeGoals-$awayGoals to $home"
      else
        s"$awayGoals-$homeGoals to $away"
    heading + gameState
  }


}
