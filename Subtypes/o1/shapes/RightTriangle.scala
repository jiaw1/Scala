package o1.shapes

// This class is introduced in Chapter 7.2.

class RightTriangle(val leg1: Double, val leg2: Double) extends Shape {

  def area = this.leg1 * this.leg2 / 2

  def hypotenuse = math.hypot(this.leg1, this.leg2)

  def perimeter = this.leg1 + this.leg2 + this.hypotenuse

}
