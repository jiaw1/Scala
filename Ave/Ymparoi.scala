import scala.io.StdIn._

object Ymparoi extends App {
  val keskiosa = readLine("Anna ympäröitävät merkit: ")
  val reuna    = readLine("Anna ympäröivät merkit: ")
  println(reuna + keskiosa + reuna)
}
