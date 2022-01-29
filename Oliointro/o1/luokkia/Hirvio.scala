package o1.luokkia

// Tämä luokka liittyy lukuun 2.4 ja esitellään siellä.

class Hirvio(val tyyppi: String, val taysiKunto: Int) {

  var nykykunto = taysiKunto

  val kuvaus = this.tyyppi + " (" + this.nykykunto + "/" + this.taysiKunto + ")"

  def vahingoitu(paljonko: Int) = {
    this.nykykunto = this.nykykunto - paljonko
  }

}
