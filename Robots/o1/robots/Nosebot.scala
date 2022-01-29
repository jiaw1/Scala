package o1.robots

import o1.CompassDir

/** The class `Nosebot` represents the "brains" (or AI) of robots that move in the
  * direction their nose points, in straight lines, only turning when they have to.
  * They always turn right.
  * @param name  the name of the nosebot
  * @param body  the robot body whose actions the nosebot brain will control */
class Nosebot(name: String, body: RobotBody) extends RobotBrain(name, body) {


  /** Checks the square that neighbors the nosebot in the given direction to see
    * if it contains something that the nosebot considers an obstacle. Nosebots
    * are careful: they consider every square that isn’t empty to be an obstacle.
    * This behavior overrides the default implementation from class `RobotBrain`.
    *
    * (A consequence of this is that a nosebot will consider itself stuck unless
    * there is at least one empty square next to it. See [[isStuck]] and [[controlTurn]].) */
  override def mayAdvance(direction: CompassDir) = this.body.neighboringSquare(direction).isEmpty


  /** Attempts to move the robot one step forward in its current heading (as per
    * [[advanceCarefully]]). Failing that, turns the robot 90 degrees clockwise instead.
    * @return `true` if the robot moved forward, `false` if the route was blocked
    *         and the robot turned instead */
  def attemptMove() = {
    val isSuccess = this.advanceCarefully()
    if (!isSuccess) {
      this.body.spinClockwise()
    }
    isSuccess
  }


  /** Moves the robot. A nosebot first looks at the next square in the direction it
    * is currently facing. If that square is empty, it moves there and ends its turn.
    * If the square was not empty, the bot turns 90 degrees clockwise and immediately
    * tries again (during the same turn): it again moves a step forward if possible,
    * and turns to check the next direction if necessary. In case the robot completes
    * a 360-degree turnabout without finding a suitable square to move in, it ends its
    * turn without changing location.
    *
    * If other words, a nosebot uses its turn to call [[attemptMove]] up to four times.
    *
    * As a nosebot always looks where it's going, so it can never collide with anything
    * during its own turn. A nosebot only ever moves a single square per turn.
    *
    * This method assumes that it is not called if the robot is broken or stuck. */
  def moveBody() = {
    var attemptNumber = 1
    while (attemptNumber <= CompassDir.Count && !this.attemptMove()) {
      attemptNumber += 1
    }
  }

  // This alternative solution interrupts the method with return:
  private def moveBody_v2(): Unit = {
    for (attemptNumber <- 1 to CompassDir.Count) {
      if (this.attemptMove()) {
        return
      }
    }
  }

  // This alternative solution uses a lazy-list:
  private def moveBody_v3(): Unit = {
    LazyList.from(1).map( _ > CompassDir.Count || this.attemptMove() ).find( _ == true )
  }

  // This version uses the fact that a list of length CompassDir.Count (i.e., 4) is always enough:
  private def moveBody_v4(): Unit = {
    LazyList.fill(CompassDir.Count)( this.attemptMove() ).find( _ == true )
  }


  // The following two simpler solutions rely on the assumption that moveBody is
  // never called unless there’s at least one possible move, so they don’t even
  // count the attempts:

  private def moveBody_v5() = {
    while (!this.attemptMove()) {
      // nothing, just try again
    }
  }

  private def moveBody_v6() = {
    LazyList.continually( this.attemptMove() ).find( _ == true )
  }

}