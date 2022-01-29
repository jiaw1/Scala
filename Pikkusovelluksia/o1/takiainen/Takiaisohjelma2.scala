package o1.takiainen
import o1._

object Takiaisohjelma2 extends App {

  val takiainen = new Takiainen

  val leveys = 800
  val korkeus = 500
  val tausta = rectangle(leveys, korkeus, White)

  val nakyma = new View(takiainen) {

    def makePic = {
      val kursori = takiainen.sijainti
      val pysty = line(new Pos(kursori.x, 0), new Pos(kursori.x, korkeus - 1), Black)
      val vaaka = line(new Pos(0, kursori.y), new Pos(leveys - 1, kursori.y),  Black)
      tausta.place(pysty, new Pos(kursori.x, 0)).place(vaaka, new Pos(0, kursori.y))
    }

    override def onMouseMove(kursori: Pos) = {
      takiainen.sijainti = kursori
      println("Hiiri liikkui kohtaan " + kursori)
    }

  }

  nakyma.start()

}