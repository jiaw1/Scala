package o1
object misc { // These definitions at the top are discussed in Chapter 5.2.

  def isAscending(numbers: Vector[Int]) = numbers.zip(numbers.tail).forall(isInOrder)

  // One of many alternative solutions:
  def isAscending2(vector: Vector[Int]) = vector.sliding(2).forall( twoElems => twoElems(0) <= twoElems(1) )


  def toMinsAndSecs(seconds: Int) = (seconds / 60, seconds % 60)


  def tempo(music: String) = {
    val slashLocation = music.indexOf("/")
    if (slashLocation >= 0) music.drop(slashLocation + 1).toInt else 120
  }

  // Some of the many alternative solutions:
  def tempo2(music: String) = music.split("/").lift(1) match {
    case Some(tempoString) => tempoString.toInt
    case None              => 120
  }
  def tempo3(music: String) = music.split("/").lift(1).getOrElse("120").toInt
  def tempo4(music: String) = music.split("/").lift(1).map( _.toInt ).getOrElse(120) // the map method will be introduced in a later chapter


  def insert(piece: String, target: String, location: Int) = {
    val front = target.take(location)
    val back = target.drop(location)
    front + piece + back
  }

  // One of many alternative solutions (using splitAt, not introduced in the chapter):
  def insert2(piece: String, target: String, location: Int) = {
    val (front, back) = target.splitAt(location)
    front + piece + back
  }


  def together(voices: Vector[String], tempo: Int) = voices.mkString("&") + "/" + tempo


  def describe(pic: Pic) = if (isPortrait(pic)) "portrait" else if (pic.width == pic.height) "square" else "landscape"

  def isPortrait(pic: Pic) = pic.height > pic.width


  def isInOrder(pairOfNumbers: (Int, Int)) = pairOfNumbers._1 <= pairOfNumbers._2    // This example function is introduced in Chapter 8.4.

}