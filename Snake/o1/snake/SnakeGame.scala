package o1.snake

import o1._
import scala.util.Random
import SnakeGame._


// This companion object provides constants and a utility function.
object SnakeGame {

  // The height and width of the game area, measured in squares that can hold
  // a single segment of a snake.
  val SizeInSquares = 40

  // Randomly returns the position of a single square on the grid, excluding the
  // very edges (where no food can appear and which kill the snake if it enters).
  def randomLocationOnGrid() = {
    new GridPos(Random.nextInt(SizeInSquares - 2) + 1,
                Random.nextInt(SizeInSquares - 2) + 1)
  }

}



// Represents games of Snake. A SnakeGame object is mutable: it tracks the
// position and heading of a snake as well as the position of a food item that
// is available for the snake to eat next.
class SnakeGame(initialPos: GridPos, initialHeading: CompassDir) {

  private var segments = Vector(initialPos)
  var snakeHeading = initialHeading
  var nextFood = randomLocationOnGrid()

  def snakeSegments = this.segments

  def isOver = {
    val head = this.segments.head
    val validCoords = 1 until SizeInSquares
    val collidedWithWall = !validCoords.contains(head.x) || !validCoords.contains(head.y)
    val collidedWithSelf = this.segments.tail.contains(head)
    collidedWithWall || collidedWithSelf
  }

  def advance() = {
    val nextHead = this.segments.head.neighbor(this.snakeHeading)
    if (nextHead == this.nextFood) {
      this.segments = nextHead +: this.segments
      this.nextFood = randomLocationOnGrid()
    } else {
      this.segments = nextHead +: this.segments.init  // .init is equivalent to .dropRight(1)
    }
  }


}
