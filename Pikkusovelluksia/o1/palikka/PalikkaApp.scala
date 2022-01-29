package o1.palikka
import o1._


// Tämä ohjelma liittyy lukuun 2.7 ja esitellään siellä.

object PalikkaApp extends App {

  val tausta = rectangle(500, 500, Black)

  val palikka = new Palikka(20, new Pos(300, 50), Gray)

  val nakyma = new View(palikka, "Palikkaohjelma") {
    def makePic = {
      val palikanKuva = rectangle(palikka.koko, palikka.koko, palikka.vari)
      tausta.place(palikanKuva, palikka.sijainti)
    }
  }

  nakyma.start()

}