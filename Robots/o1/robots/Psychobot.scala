package o1.robots

import o1._

/** The class `Psychobot` represents the "brains" (or AI) of robots which stand still
  * until they see another unbroken robot. When this happens, they ram the victim with
  * incredible speed.
  * @param name  the name of the psychobot
  * @param body  the robot body whose actions the psychobot brain will control */
class Psychobot(name: String, body: RobotBody) extends RobotBrain(name, body) {


  /** Moves the robot. During its turn, a psychobot uses its sensors to scan the four main
    * compass directions, always starting with north and continuing clockwise. (It does
    * not change facing while doing this.) When the psychobot notices an intact robot,
    * it turns to face it, instantly moves an unlimited number of squares towards the robot,
    * and rams into it, causing the victim to break. After the collision, the psychobot
    * remains in the square adjacent to its victim, and stops scanning for the rest of the
    * turn. During its next turn, it will start over by scanning north.
    *
    * If there are no victims in any of the four main directions, the robot waits still.
    * It does not attack broken robots. A psychobot can not scan through walls and it can
    * never collide with a wall. It also does not see through robots, not even broken ones. */
  def moveBody() = {
    this.directionOfVictim.foreach(this.charge)
  }


  /** Locates the first available victim (if any), starting at north and moving clockwise. */
  private def directionOfVictim = CompassDir.Clockwise.find(this.seesVictim)


  /** Determines whether this psychobot sees a suitable victim (intact robot) in the given direction. */
  private def seesVictim(dir: CompassDir) = this.firstNonEmptyIn(dir).robot.exists( _.isIntact )


  /** Finds the first non-empty square in a direction --- that is, what the bot "sees" in that direction. */
  private def firstNonEmptyIn(dir: CompassDir) = {
    var radarLocation = this.location
    do {
      radarLocation = radarLocation.neighbor(dir)
    } while (this.world(radarLocation).isEmpty)
    this.world(radarLocation)
  }

  // An alternative implementation with a lazy-list:
  private def firstNonEmptyIn_v2(dir: CompassDir) = {
    def path = LazyList.from(1).map( this.location.relative(dir, _) )
    path.map( this.world(_) ).dropWhile( _.isEmpty ).head
  }

  // This alternative implementation forms the lazy-list using pathTowards from class GridPos:
  private def firstNonEmptyIn_v3(dir: CompassDir) =
    this.location.pathTowards(dir).map( this.world(_) ).dropWhile( _.isEmpty ).head

  /** Moves in the given direction for as long as possible. */
  private def charge(dir: CompassDir) = {
    while (this.body.moveTowards(dir)) {
      // Keep charging!
    }
  }

  // Alternatively with a lazy-list:
  private def charge_v2(dir: CompassDir): Unit = {
    LazyList.continually( this.body.moveTowards(dir) ).find( _ == false )
  }

}


