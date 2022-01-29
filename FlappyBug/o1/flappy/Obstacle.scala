package o1.flappy

import o1._
import scala.util.Random

class Obstacle(val radius: Int) extends HasVelocity {

  private var currentPos = this.randomLaunchPosition()

  def pos = this.currentPos

  val velocity = Velocity(-ObstacleSpeed, 0)

  def touches(bug: Bug) = bug.distance(this) < bug.radius + this.radius

  def isActive = this.pos.x >= -this.radius

  def approach() = {
    this.currentPos = if (this.isActive) this.nextPos else this.randomLaunchPosition()
  }

  private def randomLaunchPosition() = {
    val launchX = ViewWidth + this.radius + Random.nextInt(500)
    val launchY = Random.nextInt(ViewHeight)
    Pos(launchX, launchY)
  }

  override def toString = "center at " + this.pos + ", radius " + this.radius

}
