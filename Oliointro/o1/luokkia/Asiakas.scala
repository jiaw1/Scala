package o1.luokkia

// Tämä luokka liittyy lukuun 2.6 ja esitellään siellä.

class Asiakas(val nimi: String, val asiakasnumero: Int, val email: String, val osoite: String) {

  override def toString = s"#$asiakasnumero $nimi <$email>"

}



