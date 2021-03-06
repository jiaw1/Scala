package o1.robots

import o1._
import scala.util.Random

/** The class `Staggerbot` represents the "brains" (or AI) of robots whose algorithms are not
  * sophisticated enough to beat a game of Viinaharava. These robots stagger from place to place
  * harum-scarum, relying on a (pseudo)random number generator.
  * @param name        the name of the staggerbot
  * @param body        the unlucky robot body whose actions the staggerbot brain will control
  * @param randomSeed  the seed that feeds the random generator that guides the bot */
class Staggerbot(name: String, body: RobotBody, randomSeed: Int) extends RobotBrain(name, body) {

  private val randomNumberGenerator = new Random(randomSeed)


  /** Moves the robot. A staggerbot selects a random direction and tries to move to the
    * adjacent square in that direction. If there is a wall or another robot in that
    * square, the robot collides with it and does not change squares (as per
    * [[RobotBody.moveTowards the `moveTowards` method of its body]], possibly colliding and
    * breaking itself or another robot).
    *
    * If the staggerbot collides with something, it remains facing whatever it collided
    * with and ends its turn there. In case the staggerbot did not collide with anything,
    * it again selects a new random direction and turns to face that direction (which may
    * or may not be the direction it just moved in).
    *
    * Note: this means that if (and only if) the staggerbot did not collide, it picks
    * two random directions per turn, one to move in and one to face in.
    *
    * The staggerbot selects each random direction using the following algorithm:
    *
    * 1. Use an indexed collection containing all the four `CompassDir`
    *    objects in clockwise order, starting with north.
    *
    * 2. Pick a random index. Use the `nextInt` method in `scala.util.Random`
    *    that produces a pseudorandom integer that is at least zero and lower
    *    than the method's integer parameter.
    *
    * 3. Select the direction found at the random index.
    *
    * A particular staggerbot always uses the same instance of `Random` to generate numbers.
    * It does not create a new random number generator every time this method is called.
    * Each staggerbot has its own `Random` object.
    *
    * This method assumes that it is called only if the robot is not broken or stuck. */
  def moveBody() = {
    if (this.body.moveTowards(this.randomDirection())) {
      this.body.spinTowards(this.randomDirection())
    }
  }


  /** Returns a direction based on the robot's random number generator. */
  private def randomDirection() = {
    val dirNumber = this.randomNumberGenerator.nextInt(CompassDir.Count)
    CompassDir.Clockwise(dirNumber)
  }

}