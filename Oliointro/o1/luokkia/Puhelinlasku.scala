package o1.luokkia

// Tämä luokka liittyy lukuun 2.3 ja esitellään siellä.

class Puhelinlasku(var asiakas: String) {

  private var soitetut = List[Puhelu]()

  def lisaaPuhelu(uusiPuhelu: Puhelu) = {
    this.soitetut = uusiPuhelu :: soitetut
  }

  def kokonaishinta = this.soitetut.map( _.kokonaishinta ).sum

  def erittely = "LASKU --- " + this.asiakas + ":" + this.soitetut.map( "\n - " + _.kuvaus ).mkString("")

}
