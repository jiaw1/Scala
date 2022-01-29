package o1

package object rainfall {

  def averageRainfall(data: Vector[Int]) = {
    val cleaned = data.takeWhile( _ != 999999 ).filter( _ >= 0 )
    if (cleaned.isEmpty) None else Some(cleaned.sum / cleaned.size)
  }

  def averageRainfallFromStrings(data: Vector[String]) =
    averageRainfall(data.flatMap( _.toIntOption ))

  def drySpell(data: Vector[Int], length: Int) = {
    def isDry(spell: Vector[Int]) = spell.forall( datum => datum >= 0 && datum <= 5 )
    data.sliding(length).indexWhere(isDry)
  }

}