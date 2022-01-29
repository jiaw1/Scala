package o1.avaruus
import o1._

// Tämä koodi liittyy lukuun 2.7 ja esitellään siellä.

object Avaruusohjelma extends App {

  val avaruus = new Avaruus(500)

  val tausta = rectangle(avaruus.leveys, avaruus.korkeus, Black)
  val maanKuva = circle(avaruus.maa.sade * 2, MediumBlue)
  val kuunKuva = circle(avaruus.kuu.sade * 2, Beige)

  val gui = new View(avaruus, "Yksinkertainen näkymä avaruuteen") {
    def makePic = tausta.place(maanKuva, avaruus.maa.sijainti).place(kuunKuva, avaruus.kuu.sijainti)
  }

  gui.start()

}

