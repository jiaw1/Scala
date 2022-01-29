package o1.laskuri
import o1._

// Tämä koodi liittyy lukuun 3.1 ja esitellään siellä.

object Tikitysohjelma extends App {
  val tikityslaskuri = new Laskuri(0)
  val tausta = rectangle(500, 500, Black)

  val nakyma = new View(tikityslaskuri, 50) {
    def makePic = {
      val nelio = rectangle(tikityslaskuri.arvo, tikityslaskuri.arvo, White).clockwise(tikityslaskuri.arvo)
      tausta.place(nelio, new Pos(250, 250))
    }

    override def onTick() = {
      tikityslaskuri.etene()
    }
  }

  nakyma.start()
}
