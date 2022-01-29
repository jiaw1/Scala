package o1.takiainen
import o1._

object Takiaisohjelma1 extends App {

  val takiainen = new Takiainen

  val leveys = 800
  val korkeus = 500
  val tausta = rectangle(leveys, korkeus, White)
  val takiaisenKuva = circle(40, YellowGreen)

  val nakyma = new View(takiainen) {

    def makePic = tausta.place(takiaisenKuva, takiainen.sijainti)

    override def onMouseMove(kursori: Pos) = {
      takiainen.sijainti = kursori
      println("Hiiri liikkui kohtaan " + kursori)
    }

  }

  nakyma.start()

}