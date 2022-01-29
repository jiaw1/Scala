// Seuraavista alkumäärittelyistä ei tarvitse tässä vaiheessa välittää mitään. Ne liittyvät
// siihen, miten nämä funktiot on sijoitettu pakkaukseen. Siitä aiheesta kerrotaan luvussa 5.2.
package o1
package object aliohjelmia {


  // KIRJOITA TEHTÄVISSÄ PYYDETYT FUNKTIOT IMPORT-KÄSKYN ALLE:
  import scala.math._


  def nelikko(kuva: Pic) = {
    val rivi = kuva.leftOf(kuva)
    rivi.above(rivi)
  }

  def somalianLippu(leveys: Double) = {
    val tausta = rectangle(leveys, 2 * leveys / 3, RoyalBlue)
    star(leveys * 4 / 13, White).onto(tausta)
  }

  def suomenLippu(leveys: Double) = {
    val koko = leveys / 18
    val vasen = rectangle(5 * koko,  4 * koko, White)
    val keski = rectangle(3 * koko,  4 * koko, Blue)
    val oikea = rectangle(10 * koko, 4 * koko, White)
    val ylaJaAla   = vasen.leftOf(keski).leftOf(oikea)
    val vaakaviiva = rectangle(leveys, 3 * koko, Blue)
    ylaJaAla.above(vaakaviiva).above(ylaJaAla)
  }

  def suomenLippu2(leveys: Double) = { // vaihtoehtoinen onto-toteutus
    val koko = leveys / 18
    val vasen = rectangle(5 * koko,  11 * koko, White)
    val oikea = rectangle(10 * koko, 11 * koko, White)
    val pysty = rectangle(3 * koko,  11 * koko, Blue)
    val vaaka = rectangle(leveys,     3 * koko, Blue)
    vaaka.onto(vasen.leftOf(pysty).leftOf(oikea))
  }

  def tsekinLippu(leveys: Double) = {
    val raidat = rectangle(leveys, leveys / 3, White).above(rectangle(leveys, leveys / 3, Crimson))
    val kolmio = triangle(leveys * 2 / 3, leveys / 2, MidnightBlue).clockwise(90)
    raidat.place(kolmio, CenterRight, Center)
    // tai esim.: raidat.place(kolmio, TopLeft, TopLeft)
    // tai esim.: raidat.place(kolmio, CenterLeft, CenterLeft)
  }

  def nenita(taide: Pic, naama: Pos) = taide.place(circle(15, Red), naama)



  def vasemmalta(kuva: Pic, osuus: Double) = kuva.crop(new Pos(0, 0), osuus / 100 * kuva.width, kuva.height)

  def oikealta(kuva: Pic, osuus: Double) = {
    val tuloksenLeveys = osuus / 100 * kuva.width
    kuva.crop(new Pos(kuva.width - 1 - tuloksenLeveys, 0), tuloksenLeveys, kuva.height)
  }

  def taittele(kuva: Pic, osuus: Double) = vasemmalta(kuva, osuus).leftOf(oikealta(kuva, osuus))







  def metreiksi(jalat: Double, tuumat: Double) = (tuumat + 12 * jalat) * 0.0254

  def kuutionTilavuus(sivu: Double) = sivu * sivu * sivu
  def kuutionAla(sivu: Double) = sivu * sivu * 6


  def rivi(ruutu: Int)   = ruutu / 8
  def sarake(ruutu: Int) = ruutu % 8


  def saesta(teksti: String, saestys: String) = {
    println(teksti)
    play(saestys)
  }

  def pystypalkki(leveys: Int) = rectangle(leveys, 10 * leveys, Blue)
  def pystypalkki(leveys: Int, vari: Color) = rectangle(leveys, 10 * leveys, vari)

  def kahdella(melodia: String, eka: Int, toka: Int, tauonPituus: Int) = {
    val melodiaEkalla = "[" + eka + "]" + melodia
    val melodiaTokalla = "[" + toka + "]" + melodia
    val tauko = " " * tauonPituus
    melodiaEkalla + tauko + melodiaTokalla
  }

  def kurssiarvosana(tehtavaarvosana: Int, tenttibonus: Int, aktiivisuusbonus: Int) =
    min(tehtavaarvosana + tenttibonus + aktiivisuusbonus, 5)

  def tuumiksi(metrit: Double) = metrit / 0.0254
  def kokonaisetJalat(metrit: Double)  = floor(tuumiksi(metrit) / 12)
  def tuumiaYli(metrit: Double) = tuumiksi(metrit) % 12

  def jaloiksiJaTuumiksi(metrit: Double) = {
    val tuumia = tuumiksi(metrit)
    (floor(tuumia / 12), tuumia % 12)
  }

  def liigapisteet(voitot: Int, tasapelit: Int) = voitot * 3 + tasapelit

  def joukkueenTiedot(nimi: String, voitot: Int, tasapelit: Int, tappiot: Int) = {
    val pelit = voitot + tasapelit + tappiot
    nimi + ": " + voitot    + "/" + pelit + " voittoa, " +
                  tasapelit + "/" + pelit + " tasapeliä, " +
                  tappiot   + "/" + pelit + " tappiota, " +
                  liigapisteet(voitot, tasapelit) + " pistettä"
  }

  // Tässä bonuksena toinen apufunktiota käyttävä ratkaisutapa, jossa välimerkeillä tarvitsee
  // "räplätä" vähemmässä kohdassa ja joka on siksi hieman enemmän "DRY" (ks. luku 1.4).

  def osuus(montako: Int, monestako: Int, kuvaus: String) = s"$montako/$monestako $kuvaus, "

  def joukkueenTiedot2(nimi: String, voitot: Int, tasapelit: Int, tappiot: Int) = {
    val pelit = voitot + tasapelit + tappiot
    nimi + ": " + osuus(voitot,    pelit, "voittoa") +
                  osuus(tasapelit, pelit, "tasapeliä") +
                  osuus(tappiot,   pelit, "tappiota") +
                  liigapisteet(voitot, tasapelit) + " pistettä"
  }

  def sanallinenArvosana(tehtavaarvosana: Int, tenttibonus: Int, aktiivisuusbonus: Int) = {
    val kuvaukset = Buffer("hylätty", "välttävä", "tyydyttävä", "hyvä", "erittäin hyvä", "erinomainen")
    kuvaukset(kurssiarvosana(tehtavaarvosana, tenttibonus, aktiivisuusbonus))
  }

  def tuplaaPisteet(pelaajat: Buffer[Int], kenen: Int) = {
    pelaajat(kenen - 1) *= 2            // sama asia kuin: pelaajat(kenen - 1) = pelaajat(kenen - 1) * 2
  }

  def sakko(pelaajat: Buffer[Int], kenen: Int, maara: Int) = {
    val toteutuva = min(maara, pelaajat(kenen - 1) - 1)
    pelaajat(kenen - 1) -= toteutuva    // sama asia kuin: pelaajat(kenen - 1) = pelaajat(kenen - 1) - toteutuva
    toteutuva
  }



  // TÄSSÄ ON ESIMERKKIFUNKTIOITA, JOIDEN TOTEUTUSTA KATSOTAAN LUVUISSA 1.7 JA 1.8.
  // NE ON SELITETTY TARKEMMIN LUKUJEN TEKSTISSÄ.

  def keskiarvo(eka: Double, toka: Double) = (eka + toka) / 2

  def huuda(lausahdus: String) = lausahdus + "!"

  def haukiOnKala(loppukaneetti: String) = {
    println("Kun hauki on vähärasvainen, sitä voidaan säilyttää pakastettuna jopa 6 kuukautta.")
    println("Vertailun vuoksi mainittakoon, että haukea rasvaisemman lahnan vastaava")
    println("säilymisaika on vain puolet eli 3 kuukautta.")
    println(loppukaneetti)
  }

  def piiri(sade: Double) = 2 * Pi * sade  // ei nyt käytössä luvuissa

  def etaisyys(x1: Double, y1: Double, x2: Double, y2: Double) = hypot(x2 - x1, y2 - y1)

  def punapallo(koko: Int) = circle(koko, Red)

  def isoinEtaisyys(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double) = {
    val eka = etaisyys(x1, y1, x2, y2)
    val toka = etaisyys(x1, y1, x3, y3)
    val kolmas = etaisyys(x2, y2, x3, y3)
    max(max(eka, toka), kolmas)
  }

  def verot(tulot: Double, tuloraja: Double, perusprosentti: Double, lisaprosentti: Double) = {
    val perusosa = min(tuloraja, tulot)
    val lisaosa = max(tulot - tuloraja, 0)
    perusosa * perusprosentti + lisaosa * lisaprosentti
  }

  def kokeilu1(luku: Int) = {
    println("Luku on: " + luku)
  }

  def kokeilu2(lukuja: Buffer[Int]) = {
    lukuja(0) = 100
  }

  def kokeilu3(luku: Int) = {
    println("Luku on: " + luku)
    luku + 1
  }

  def kokeilu4(sana: String) = {
    var luku = 1
    println(sana + ": " + luku)
    luku = luku + 1
    println(sana + ": " + luku)
    luku = luku + 1
    println(sana + ": " + luku)
    luku
  }

  def kokeilu5(aluksi: Int) = {
    var luku = aluksi
    luku = luku + 1
    luku = luku + 1
    luku = luku + 1
    luku
  }

  def testi1(teksti: String) = {
    println(teksti)
    "aina tämä"
  }

  def testi2(teksti: String) = {
    println(teksti)
    val vastaus = testi1(huuda(teksti))
    testi1(teksti)
    println("saatiin:")
    println(vastaus)
  }






  // ALLA ON FUNKTIOITA, JOITA KÄYTETÄÄN LUVUSSA 1.6. NIIDEN SISÄISTÄ TOIMINTAA EI TARVITSE ALUKSI YMMÄRTÄÄ.
  // Seuraavaa koodia ei ole kirjoitettu aloittelijaystävälliseen tyyliin.


  def poistaNegatiiviset(lukuja: Buffer[Int]): Unit = {
    lukuja --= lukuja.filter( _ < 0 )
  }


  def imdbLeffa(sija: Int) = movieData.sortBy( _._3 ).apply(sija - 1)._1

  def imdbAikavalinParas(alkuvuosi: Int, loppuvuosi: Int) =
    movieData
      .filter( leffa => leffa._2 >= alkuvuosi && leffa._2 <= loppuvuosi )
      .sortBy( _._3 )
      .apply(0)._1

  def imdbParhaatOhjaajat(leffojaVahintaan: Int) = {
    import o1.util.ConvenientCollection
    movieData
      .flatMap { case (_, _, _, _, ohjaajat) => ohjaajat.toList }
      .frequencies
      .filter( _._2 >= leffojaVahintaan )
      .toList.sortBy( -_._2 )
      .map { case (ohjaaja, leffoja) => s"$ohjaaja ($leffoja)" }
      .mkString(", ")
  }

  private lazy val movieData = {
    val Subdir   = "top_movies"
    val FileName = "omdb_movies_2015.txt"
    val rawLines = o1.util.readFileLines(s"$Subdir/$FileName").getOrElse( throw new Exception(s"Could not read the file $FileName, which is expected to be under $Subdir.") )
    val linesAsTokens = rawLines.map( _.split(";") )
    linesAsTokens.map( tokens => (tokens(0), tokens(1).toInt, tokens(2).toInt, tokens(3).toDouble, tokens(4).split(",")) )
  }


  def editointietaisyys(teksti1: String, teksti2: String) = o1.util.editDistance(teksti1, teksti2, 12)


  def animoi(kuvat: Buffer[Pic], kuviaSekunnissa: Double): Unit = {
    Animation.show(frames = kuvat, frameRate = kuviaSekunnissa)
  }

  def animoiFunktiolla(picGeneratingFunction: Int => Pic, numberOfPics: Int, picsPerSecond: Double): Unit = {
    Animation.generate(picGeneratingFunction, numberOfPics, picsPerSecond)
  }


  def kaanon(biisi: String, soittimet: Iterable[Int], viive: Int) = {
    import o1.sound.midi._
    import o1.util._

    val (melodia, tempo) = biisi match {
      case r"(.*?)$melodia(?:/([\d]+)$tempoOrNull)?" => (melodia, Option(tempoOrNull))
    }
    def alkutauko(monesko: Int) = " " * (monesko * viive atLeast 0 atMost melodia.length)
    val eriaikaiset = for ((soitin, monesko) <- soittimet.take(MaxVoices).zipWithIndex)
                        yield s"${alkutauko(monesko)}[$soitin]$melodia"
    eriaikaiset.mkString("&") + tempo.map( "/" + _ ).getOrElse("")
  }


  def sensuroi(teksti: String, rumatSanat: Iterable[String]) = {
    def piip(pituus: Int) = "[P" + "I" * max(0, pituus - 2) + "P]"
    def piippaaSana(teksti: String, rumaSana: String) = teksti.replaceAll(rumaSana, piip(rumaSana.length))
    rumatSanat.foldLeft(teksti)(piippaaSana)
  }


  def pelaaPylpyrapelia(pelaaja: String) = {
    o1.gui.O1SwingDefaults()
    import o1.gui.Dialog._
    display("Tervetuloa PYLPYRÄÄTTÖRIIN, " + pelaaja + "!\nAlussa on kaksitoista pylpyrää.\n" +
            "Pelaajat ottavat vuorotellen 1 tai 2 pylpyrää.\nViimeisen pylpyrän saanut voittaa.", Centered)
    LazyList.iterate(12)(pelaaKierros).takeWhile(peliJatkuu).force
    display("Valitettavasti hävisit. Sori, " + pelaaja + ".\n", Centered)

    def pelaaKierros(jaljella: Int) = {
      pyydaValinta(jaljella).map( valittu => konePelaa(jaljella - valittu) ).getOrElse(0)
    }

    def onSallittuMaara(maara: Int) = maara == 1 || maara == 2

    def pyydaValinta(jaljella: Int) = {
      requestInt("Jäljellä on " + jaljella + " pylpyrää. Montako otat?", onSallittuMaara, "Ota 1 tai 2.", Centered)
    }

    def konePelaa(jaljella: Int) = {
      val koneOtti = parasValinta(jaljella)
      display("Otan " + koneOtti + " " + (if (koneOtti == 1) "pylpyrän" else "pylpyrää") + ".", Centered)
      jaljella - koneOtti
    }

    def peliJatkuu(jaljella: Int) = jaljella > 0

    def parasValinta(jaljella: Int) = jaljella % 3
  }


  def nayta(sana: String) = {
    println("Parametriksi saatiin: " + sana + ".")
    sana.length
  }


  def pallonTilavuus(sade: Double) = 4 * Pi * pow(sade, 3) / 3

}
