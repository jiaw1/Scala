package o1.block
import o1._

// This class is introduced in Chapter 2.7.

class Block(val size: Int, val location: Pos, val color: Color) {
  override def toString = this.color.toString + " block at " + this.location
}

