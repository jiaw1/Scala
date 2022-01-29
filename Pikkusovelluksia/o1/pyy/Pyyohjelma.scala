package o1.pyy
import o1._

// Tämä koodi liittyy lukuun 3.4 ja esitellään siellä.

object Pyyohjelma extends App {
  val valkoinenTausta = rectangle(600, 600, White)
  val maailmanlopunKuva = rectangle(600, 600, Black)

  val pyy = new Pyy

  val nakyma = new View(pyy, 50) {

    def makePic =
      if (this.isDone) maailmanlopunKuva else pyy.kuvaksi.onto(valkoinenTausta)
      // tai: if (pyy.loppuKoitti) ...

    override def onTick() = {
      pyy.pienene()
    }

    override def isDone = pyy.loppuKoitti

  }

  nakyma.start()
}
