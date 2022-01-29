package o1.taide
import o1._


// Tämä koodi liittyy lukuun 3.1 ja esitellään siellä.

object Piirustusohjelma extends App {

  val teos = new Taideprojekti(rectangle(600, 600, White))

  val nakyma = new View(teos) {
    def makePic = teos.kuva

    override def onMouseMove(kursori: Pos) = {
      teos.piirra(kursori)
    }

    override def onClick(kursori: Pos) = {
      teos.vaihdaVaria()
    }
  }

  nakyma.start()

}


// Tämä versio liittyy tehtävän jatko-osaan, Piirustusohjelma 3:een

object Piirustusohjelma2 extends App {

  val teos = new Taideprojekti2(rectangle(600, 600, White))

  val nakyma = new View(teos) {
    def makePic = teos.kuva

    override def onMouseMove(kursori: Pos) = {
      teos.piirra(kursori)
    }

    override def onClick(klikkaustapahtuma: MouseClicked) = {
      teos.vaihdaVaria(klikkaustapahtuma.clicks)
    }
  }

  nakyma.start()

}

