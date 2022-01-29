package o1.takiainen
import o1._

object Takiaisohjelma3 extends App {

  val takiainen = new Takiainen

  val leveys = 800
  val korkeus = 500
  val keskus = new Pos(leveys / 2, korkeus / 2)
  val tausta = rectangle(leveys, korkeus, White)

  val nakyma = new View(takiainen) {

    def makePic = {
      val viivanPaa = keskus.add(takiainen.sijainti).multiply(0.5)
      tausta.place(line(keskus, viivanPaa, Black), keskus)
    }

    override def onMouseMove(kursori: Pos) = {
      takiainen.sijainti = kursori
      println("Hiiri liikkui kohtaan " + kursori)
    }

  }

  nakyma.start()

}