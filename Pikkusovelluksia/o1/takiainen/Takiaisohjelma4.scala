package o1.takiainen
import o1._

object Takiaisohjelma4 extends App {

  val takiainen = new Takiainen

  val leveys = 800
  val korkeus = 500
  val tausta = rectangle(leveys, korkeus, White)
  val takiaisenKuva = circle(40, YellowGreen)

  val nakyma = new View(takiainen) {

    var viimeisinKursori = takiainen.sijainti  // tuoreimman säilyttäjä

    def makePic = tausta.place(takiaisenKuva, takiainen.sijainti)

    override def onMouseMove(kursori: Pos) = {
      this.viimeisinKursori = kursori
    }

    override def onTick() = {
      takiainen.sijainti = takiainen.sijainti.multiply(9).add(this.viimeisinKursori).divide(10)
      // myös tämä toimii: takiainen.sijainti = (takiainen.sijainti * 9 + this.viimeisinKursori) / 10
    }

  }

  nakyma.start()

}