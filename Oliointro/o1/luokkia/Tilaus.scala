package o1.luokkia

// Luokan tämä versio esitellään luvussa 4.4.

/** Luokka `Tilaus` kuvaa tuotetilauksia (kuvitteellisessa alkeellisessa verkkokaupassa).
  * Tämä yksinkertainen toteutus ei pidä tarkkaa kirjaa kaikista tilaukseen lisätyistä
  * tuotteista vaan vain niiden kokonaishinnasta.
  *
  * @param numero   tilauksen tunnistenumero (positiivinen kokonaisluku)
  * @param tilaaja  tilauksen tehnyt asiakas
  * @param osoite   osoite, johon tilaus mahdollisesti toimitetaan asiakkaan osoitteen sijaan */
class Tilaus(val numero: Int, val tilaaja: Asiakas, val osoite: Option[String]) {

  private var lisattyjenHinta = 0.0   // kokooja

  /** tieto siitä, onko tuote haluttu erityisen nopeasti (`true`) vai ei (`false`) */
  var onPika = false            // tuoreimman säilyttäjä


  /** Palauttaa tilaukseen toistaiseksi lisättyjen tuotteiden kokonaishinnan. */
  def kokonaishinta = this.lisattyjenHinta


  /** Lisää tilaukseen tietyn määrän tuotetta parametrien mukaisesti.
    * Käytännössä tämä tarkoittaa sitä, että tilauksen kokonaishintaa muokataan
    * lisättyjen tuotteiden yhteishinnan verran.
    *
    * @param kappalehinta  lisättyjen tuotteiden hinta kappaleelta
    * @param lukumaara     lisättyjen tuotteiden lukumäärä */
  def lisaaTuote(kappalehinta: Double, lukumaara: Int) = {
    this.lisattyjenHinta = this.lisattyjenHinta + kappalehinta * lukumaara
  }


  /** Palauttaa osoitteen, johon tilaus toimitetaan. Jos tilaukselle on kirjattu
    * osoite, palautetaan kyseinen osoite merkkijonona. Jos ei, palautetaan tilauksen
    * tehneen asiakkaan osoite. */
  def toimitusosoite = this.osoite.getOrElse(this.tilaaja.osoite)


  /** Palauttaa kuvauksen tilauksesta. */
  override def toString = {
    val perustiedot = s"tilaus $numero, tilaaja: $tilaaja, yhteensä $kokonaishinta euroa"
    val toimitustiedot = this.osoite match {
      case None            => "asiakkaan osoitteeseen"
      case Some(muuOsoite) => "osoitteeseen " + muuOsoite
    }
    s"$perustiedot, $toimitustiedot"
  }

}

