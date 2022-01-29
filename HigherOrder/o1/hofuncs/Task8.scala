package o1.hofuncs

// This program is introduced in Chapter 6.1.

import o1._

object Task8 extends App {

  def transformEachElement(strings: Buffer[String], transform: String => String) = {
    for (index <- strings.indices) {
      strings(index) = transform(strings(index))
    }
  }


  // Abbreviates a two-word name. For example: "Sauli Niinistö" --> "S. Niinistö"
  def shortenName(name: String) = {
    val pieces = name.split(" ")
    pieces(0).take(1) + ". " + pieces(1)
  }

  val exampleNames = Buffer("Umberto Eco", "James Joyce", "Dorothy Dunnett")
  println(exampleNames.mkString(", "))
  transformEachElement(exampleNames, shortenName)
  println(exampleNames.mkString(", "))

}

