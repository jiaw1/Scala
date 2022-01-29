package o1.flappy

import o1._

class Bug(initialPos: Pos) extends HasVelocity {

  val radius = BugRadius
  private var yVelocity = 0.0
  private var currentPos = initialPos

  def velocity = Velocity(0, this.yVelocity)

  def pos = this.currentPos

  def flap(strength: Double) = {
    this.yVelocity = -strength
  }

  def fall() = {
    if (this.currentPos.y < GroundY) {
      this.yVelocity = this.yVelocity + Gravity
    }
    this.currentPos = this.nextPos.clampY(0, 350)
  }

  def isInBounds = this.pos.y < 350 && this.pos.y > 0

  override def toString = "center at " + this.pos + ", radius " + this.radius

}

