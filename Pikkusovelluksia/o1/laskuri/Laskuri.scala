package o1.laskuri
import o1._

// Tämä luokka liittyy lukuun 3.1 ja esitellään siellä.

class Laskuri(var arvo: Int) {

  def etene() = {
    this.arvo = this.arvo + 1
  }

  override def toString = "arvo " + this.arvo

}
