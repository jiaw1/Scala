
package o1.stars

import o1._


/** Each `Star` object represents a star on a star map. It records some basic information
  * (an identifier, a location, and a magnitude); in addition, some but not all stars have
  * been assigned a name. (In reality, many stars have multiple alternative names, but this
  * class does not capture that fact.)
  *
  * @param id         a number that uniquely identifies the star from the other visible stars (a so-called Henry Draper number)
  * @param coords     the location of the star on a two-dimensional star map
  * @param magnitude  the apparent magnitude (brightness) of the star: ''smaller means brighter!''
  * @param name       the star's name (wrapped in `Some`), or `None` */
class Star(val id: Int, val coords: StarCoords, val magnitude: Double, val name: Option[String]) {


  /** Determines the location of this star within a given image of a star map.
    * This method effectively converts the `coords` of this star (a `StarCoords`) into
    * a `Pos` within the given image. For instance, if this star has `coords` of (0,0),
    * and a 400-by-400 pixel image is given, this method returns a `Pos` of (200,200).
    * @see [[StarCoords.toImagePos]] */
  def posIn(skyPic: Pic) = this.coords.toImagePos(skyPic)


  /** Returns a string description of the star. The format of the string varies slightly
    * depending on whether the star has a name or not. For a named star, the description has
    * the form "#ID NAME (COORDS)"; e.g., "#39801 Betelgeuse (x=0.02, y=0.99)". In case of an
    * unnamed star, the name and one of the spaces are omitted; e.g.,"#39810 (x=0.016, y=0.30)". */
  override def toString = {
    val nameString = this.name match {
      case Some(name) => name + " "
      case None       => ""
    }
    s"#$id $nameString($coords)"
  }
  // alternatively, e.g.:
  // override def toString = {
  //  val nameString = this.name.map( _ + " " ).getOrElse("")
  //  s"#$id $nameString($coords)"
  //}

}



