package o1.sananmuunnos

/** Kukin `Sana`-tyyppinen olio kuvaa yhtä suomen kielen sanaa. Tavallisesta merkkijonosta `Sana`-olio
  * eroaa eritoten siten, että sillä on metodi `muunnos`, jonka avulla voi muodostaa lasten ja
  * lapsenmielisten suosimia '''sananmuunnoksia'''. Sananmuunnos saadaan yhdistämällä kahden sanan äänteitä
  * (tässä: merkkejä) tiettyjen sääntöjen mukaan.
  *
  * Sananmuunnos muodostetaan pääpiirteissään siten, että otetaan kaksi sanaa, vaihdetaan niiden
  * alkupään merkkejä keskenään ja laitetaan syntyneet sanat peräkkäin. Prosessiin liittyy kuitenkin
  * koko joukko yksityiskohtia, joista lisää alla.
  *
  * ==Sananmuunnoksen muodostamisen perusteet==
  *
  * Helppo perustapaus: sanat ovat "henri" and "kontinen". Otetaan kummastakin ensimmäinen konsonantti
  * ja sitä seuraava vokaali: saadaan "he" ja "ko". Vaihdetaan päittäin ja saadaan sananmuunnos:
  *
  * `henri kontinen --> konri hentinen`
  *
  * `tarja halonen --> harja talonen`
  *
  * Jos sana alkaa usealla peräkkäisellä konsonantilla, siirretään ne kaikki toiseen sanaan.
  * Jos taas konsonantteja ei alussa ole, vain ensimmäinen vokaali siirtyy:
  *
  * `frakki kontti --> kokki frantti`
  *
  * `ovi kello --> kevi ollo`
  *
  * Yllä olevissa esimerkeissä kaikki vokaalit olivat "lyhyitä" eli niissä oli vain yksi merkki.
  * Jos sama ensivokaalimerkki esiintyy sanassa useasti peräkkäin, muodostuu "pitkä" vokaali.
  * Vokaalin pituus säilyy samana myös toiseen sanaan siirtäessä, vaikka itse vokaalimerkki
  * voikin vaihtua:
  *
  * `haamu kontti -> koomu hantti`
  *
  * Tarkempi selonteko edellisestä: Sanassa "haamu" on pitkä vokaali "aa". Kun sanan alkupää
  * korvataan kirjaimilla k ja o, säilyy vokaalin pituus ja saadaan "koomu" eikä "komu".
  * Sanassa "kontti" on puolestaan lyhyt vokaali "o". Kun sen alkupää korvataan merkeillä
  * h ja a, saadaan "hantti" eikä "haantti".
  *
  * Vaikka ensimmäisessä tavussa saattaakin olla useita eri vokaaleja, vain ensimmäisellä on
  * tässä merkitystä:
  *
  * `hauva kontti --> kouva hantti`
  *
  * `puoskari kontti --> kooskari puntti`
  *
  * ==Vokaalisointu==
  *
  * Suomessa on kahdenlaisia vokaaleja: '''etuvokaaleja''' (e, i, ä, y, ö) ja '''takavokaaleja'''
  * (a, u, o). (Nimet liittyvät siihen, missä osassa suuta vokaalit äännetään.) Johtuen
  * ''vokaalisoinnuksi'' kutsutusta kielellisestä ilmiöstä takavokaalit a, u ja o eivät
  * tavallisesti esiinny samassa sanassa niitä vastaavien etuvokaalien ä, y ja ö kanssa.
  *
  * sananmuunnoksiin vokaalisointu liittyy seuraavasti. Jos mikä tahansa takavokaaleista a, u tai o
  * sijoitetaan sanan alkuun, niin kaikki sanassa esiintyvät etuvokaalit ä, y ja ö on vaihdettava
  * niitä vastaaviksi takavokaaleiksi. Niin käy tässä y- ja ä-kirjaimille, joka muuttuvat u:ksi ja
  * a:ksi.
  *
  * `köyhä kontti --> kouha köntti`
  *
  * Sama toimii myös toisin päin eli etuvokaaleista takavokaaleiksi. Tässä syntyvän sananmuunnoksen
  * ensimmäiseen osaan tulee vain etuvokaaleita ja toiseen vain takavokaaleita:
  *
  * `hauva läähättää --> läyvä haahattaa`
  *
  * Äänteen muuntumista toisen kaltaiseksi sanotaan '''assimilaatioksi'''. Vokaalit e ja i
  * eivät assimiloidu suuntaan tai toiseen eivätkä aiheuta assimilaatiota toisissa vokaaleissa.
  *
  * Huomaa, että vokaalisointua "sovelletaan" jo ennen kuin sananmuunnoksen kaksi osaa yhdistetään.
  * Vokaalisointu ei siis aiheuta muutoksia "sanarajojen yli", kuten yllä olevasta esimerkistä voi
  * havaita.
  *
  * ==Lisäesimerkkejä==
  *
  * Alla on vielä muutama esimerkki sananmuunnoksista:
  *
  * `frakki stressi --> strekki frassi`
  *
  * `äyskäri kontti --> kouskari äntti`
  *
  * `hattu sfääri --> sfätty haari`
  *
  * `ovi silmä --> sivi olma`
  *
  * `haamu prätkä --> präämy hatka`
  *
  * `puoskari sfääri --> sfäöskäri puuri`
  *
  * `puoskari äyskäri --> äöskäri puuskari`
  *
  * ==Tämä riittäköön tähän==
  *
  * Todellisen elämän sananmuunnoksiin liittyy tässä kuvattujen lisäksi vielä eräitä muitakin
  * sääntöjä, mutta niitä ei käsitellä tai käytetä tässä. On erilaisia erikoistapauksia kuten lainasanat,
  * jotka eivät noudata vokaalisointua ("vampyyri"), sekä yhdyssanat ("jääkiekko").
  * Tämän luokan toteutuksessa kuitenkin tällaiset tapaukset jätetään huomioimatta ja oletetaan,
  * että vokaalisointu on aina tiukasti voimassa.
  *
  * @param merkkijono  sana, joka toimii toisena puoliskona sananmuunnoksessa (esim. "kontti").
  *                    Tämä merkkijono saa sisältää isojakin kirjaimia, mutta `Sana`-olio
  *                    tulkitsee kaikki kirjaimet kuin ne olisivat pieniä. */
class Sana(merkkijono: String) {
                                                                                           // Kun merkkijono on "Struuma":
  private val merkit      = merkkijono.toLowerCase                                         // merkit = "struuma"
  private val konsonantit = this.merkit.takeWhile( onKonsonantti(_) )                      // konsonantit = "str"
  private val vokaali     = this.merkit(konsonantit.length)                                // vokaali = 'u'
  private val vokaalit    = this.merkit.drop(konsonantit.length).takeWhile( _ == vokaali ) // vokaalit = "uu"
  private val loppuosa    = this.merkit.drop(konsonantit.length + vokaalit.length)         // loppuosa = "ma"


  /** Palauttaa sellaisen hybridin tästä sanasta ja annetusta sanasta, jossa on annetun
    * sanan alku ja tämän sanan loppu assimiloituna. Esimerkiksi jos tämä sana on "haamu"
    * ja toinen sana on "prätkä", niin palautetaan "präämy". */
  private def puolimuunnos(toinen: Sana) =
    toinen.konsonantit + toinen.vokaali.toString * this.vokaalit.length + this.loppuosa.map( assimiloi(_, toinen.vokaali) )


  /** Palauttaa sananmuunnoksen, joka saadaan yhdistämällä tämä sana annettuun toiseen sanaan
    * tavalla, joka selitetään tämän luokan esittelyssä. Tuloksessa esiintyy vain pieniä merkkejä.
    * Esimerkiksi jos tämän sanan merkit ovat `"tarja"` ja toisen sanan `"halonen"`, palauttaa
    * merkkijonon `"harja talonen"`. */
  def muunnos(toinen: Sana) = this.puolimuunnos(toinen) + " " + toinen.puolimuunnos(this)


  /** Palauttaa kuvauksen, josta näkyvät ne tämän sanan osat, jotka ovat sananmuunnoksen tekemisen
    * kannalta oleellisia: 1) alun konsonantit (jos niitä on), 2) ensimmäinen vokaalimerkki ja sitä
    * seuraavat identtiset vokaalit, sekä 3) loppuosa. (Ks. tämän luokan kuvaus yltä.) Kuvauksessa näiden
    * osien väliin laitetaan `|`-merkit. Esimerkiksi sanan ollessa `"struuma"` kuvaus on `"str|uu|ma"`,
    * ja sanan ollessa `"joulu"` kuvaus on `"j|o|ulu"`. Jos sanan alussa ei ole konsonantteja, alkaa
    * palautettu merkkijono `|`-merkillä: `"|a|lku"`. */
  override def toString = this.konsonantit + "|" + this.vokaalit + "|" + this.loppuosa

}


/** Tämä `Sana`-luokan kumppaniolio tarjoaa yhden metodin, jonka avulla sananmuunnoksia on hieman kätevämpi tehdä.
  * @see [[Sana]]-luokka */
object Sana {

  /** Palauttaa sananmuunnoksen kahdesta parametrimerkkijonostaan. Esimerkiksi jos parametriarvot
    * ovat `"Tarja"` ja `"Halonen"`, palauttaa `"harja talonen"`. */
  def muunnos(ekaSana: String, tokaSana: String) = new Sana(ekaSana).muunnos(new Sana(tokaSana))

}
