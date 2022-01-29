package o1.flappy

import o1._

class Game {

  val bug       = new Bug(new Pos(ViewWidth / 10, ViewHeight / 10))
  val obstacles = Vector(new Obstacle(70), new Obstacle(30), new Obstacle(20))

  def activateBug() = {
    this.bug.flap(FlapStrength)
  }

  def timePasses() = {
    this.obstacles.foreach( _.approach() )
    this.bug.fall()
  }

  def isLost = !this.bug.isInBounds || this.obstacles.exists( _.touches(bug) )

}
