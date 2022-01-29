package o1.luokkia

// Tämä luokka liittyy lukuun 2.4 ja esitellään siellä.

class Henkilo(val nimi: String) {

  def lausu(lause: String) = this.nimi + ": " + lause

  def reagoiSrirachaan     = this.lausu("Onpa hyvä kastike.")

  def reagoiKryptoniittiin = this.lausu("Onpa kumma mineraali.")
}


