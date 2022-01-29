package o1.palikka
import o1._

// Tämä luokka liittyy lukuun 2.7 ja esitellään siellä.

class Palikka(val koko: Int, val sijainti: Pos, val vari: Color) {
  override def toString = this.vari.toString + " block at " + this.sijainti
}

