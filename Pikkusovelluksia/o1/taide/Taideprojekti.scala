package o1.taide
import o1._


// Tämä koodi liittyy lukuun 3.1 ja esitellään siellä.

class Taideprojekti(tausta: Pic) {
  var varinumero = 0                              // askeltaja: 0, 1, 2, 3, 0, 1, 2, 3, ...
  val paletti = Buffer(Black, Red, Green, Blue)   // kiintoarvo

  var kuva = tausta                               // kokooja
  var pensseli = circle(10, this.piirtovari)      // tuoreimman säilyttäjä

  def piirtovari = this.paletti(this.varinumero)

  def vaihdaVaria() = {
    this.varinumero = (this.varinumero + 1) % 4
    this.pensseli = circle(10, this.piirtovari)
  }

  def piirra(mihin: Pos) = {
    this.kuva = this.kuva.place(this.pensseli, mihin)
  }

}


// Tämä on ratkaisu tehtävän jatko-osaan, Piirustusohjelma 3:een

class Taideprojekti2(tausta: Pic) {
  var varinumero = 0                            // tuoreimman säilyttäjä
  val paletti = Buffer(Black, Red, Green, Blue) // kiintoarvo

  var kuva = tausta                             // kokooja
  var pensseli = circle(10, this.piirtovari)    // tuoreimman säilyttäjä

  def piirtovari = this.paletti(this.varinumero)

  def vaihdaVaria(monesko: Int) = {
    this.varinumero = (monesko - 1).abs % 4
    this.pensseli = circle(10, this.piirtovari)
  }

  def piirra(mihin: Pos) = {
    this.kuva = this.kuva.place(this.pensseli, mihin)
  }

}

