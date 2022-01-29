package o1.football4


/** Each object of the class `Match` represents a state of a football match.
  * A match is played between teams from two clubs: a home club and an away club.
  * Goals scored by players of either team can be added to the match object with the
  * method `addGoal`, '''which produces a new `Match` object representing the updated
  * state of the match. Each `Match` object is immutable.'''
  *
  * @param home         the club whose team plays at home in the match
  * @param away         the club whose team plays away in the match
  * @param homeScorers  '''the players who have scored goals for the home team so far'''
  *                     (in order for each goal; the same player appears multiple times
  *                     if they have scored multiple goals)
  * @param awayScorers  '''the players who have scored goals for the away team so far'''
  *                     (in order for each goal; the same player appears multiple times
  *                     if they have scored multiple goals) */
class Match(val home: Club, val away: Club, val homeScorers: Vector[Player], val awayScorers: Vector[Player]) {

  /** Returns the club whose team won the match (or is about to win it, assuming the current
    * result stands). That is, returns the club who has scored more goals than the other one.
    * The club is wrapped in an `Option`; `None` is returned if the game is tied. */
  def winner = {
    if (this.goalDifference < 0)
      Some(this.away)
    else if (this.goalDifference > 0)
      Some(this.home)
    else
      None
  }


  /** Returns the name of the club whose team won the match (or is about to win it, assuming
    * the current result stands). If the game is tied, returns the string "no winner". */
  def winnerName = this.winner.map( _.name ).getOrElse("no winner")


  /** Returns the number of goals that have been scored (so far) by the home team. */
  def homeGoals = this.homeScorers.size


  /** Returns the number of goals that have been scored (so far) by the away team. */
  def awayGoals = this.awayScorers.size



  /** Records a goal as having been scored by the given player and '''produces a match object that
    * represents the updated state of the match'''. This assumes that the given player plays for
    * one of the participating clubs; if not, this method simply returns unmodified match object itself.
    * @param scorer  the goalscoring player (who scores the goal for his or her own team)
    * @return '''a new match object that is otherwise the same as this one, but has one more goal''' */
  def addGoal(scorer: Player) = {
    if (scorer.employer == this.home) {
      new Match(this.home, this.away, this.homeScorers :+ scorer, this.awayScorers)
    } else if (scorer.employer == this.away) {
      new Match(this.home, this.away, this.homeScorers, this.awayScorers :+ scorer)
    } else {
      this
    }
  }


  /** Returns the player who scored the so-called "winning goal". The winning goal of
    * a match is the first goal for the winning team that was "necessary for the win"
    * in light of the scoreline. For instance, if the score is 4-2 to the home team,
    * then the third goal scored by the home team is the "winning goal". If scores are
    * even, there is no winning goal or winning scorer.
    * @return the scorer of the winning goal, wrapped in an `Option`;
    *         `None` if there is no winning goal */
  def winningScorer = {
    if (this.goalDifference < 0)
      Some(this.awayScorers(this.homeGoals))
    else if (this.goalDifference > 0)
      Some(this.homeScorers(this.awayGoals))
    else
      None
  }


  /** Returns the goal difference of the match. The sign of the number indicates
    * which team scored more goals.
    * @return the goal difference as a positive number if the home team won, a
    *         negative number if the away team won, or zero in case of a tied match */
  def goalDifference = this.homeGoals - this.awayGoals


  /** Determines whether this match has a higher total score than another given match.
    * @return `true` if more goals were scored in total in this match than in the given match, `false` otherwise */
  def isHigherScoringThan(anotherMatch: Match) = this.totalGoals > anotherMatch.totalGoals


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


  /** Returns a vector that contains all the players who scored in the match. In the
    * vector, all the players who scored for the home team appear (in the order in which
    * they scored) before the players who scored for the away team (also in scoring order
    * amongst themselves). A player will appear in the vector as many times as they scored. */
  def allScorers = this.homeScorers ++ this.awayScorers


  /** Returns a boolean value indicating whether the given player scored in this match. */
  def hasScorer(possibleScorer: Player) = this.allScorers.contains(possibleScorer)

}


