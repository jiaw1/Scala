package o1.shapes

// This class is introduced in Chapter 7.2.

import scala.collection.mutable.Buffer

object ShapeTest extends App {

  val shapes = Buffer(new Circle(10), new Rectangle(10, 100), new Circle(5))

  var sumOfAreas = 0.0
  for (current <- shapes) {
     sumOfAreas += current.area
  }
  println("The sum of the areas is: " + sumOfAreas)

}

