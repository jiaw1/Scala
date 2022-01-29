
package o1.stars.io

import o1._
import o1.stars._


/** This singleton object provides utilities for creating and manipulating images that
  * represent views of a night sky. */
object SkyPic {


  /** Given a [[StarMap]] that details what is visible in the sky, produces a [[Pic]]
    * that represents that information as an image. The background of the image is a
    * `Black` square of the given size. Each star and constellation in the sky appear
    * against that background.
    *
    * @param skyData  the contents of the night sky that are to be represented as an image
    * @param bgSize   the width and height, in pixels, of the desired square image */
  def create(skyData: StarMap, bgSize: Int) = {
    val darkSky = rectangle(bgSize, bgSize, Black)
    val skyWithConstellations = skyData.constellations.foldLeft(darkSky)(placeConstellation)
    skyData.stars.foldLeft(skyWithConstellations)(placeStar)
  }


  /** Given an image of the sky and a star, returns a version of the original image with
    * an image of the star placed on top. That is, forms an image of the star and places
    * it against the given (larger) image in a `Pos` that corresponds to the star's
    * [[StarCoords]].
    *
    * The star is depicted as a `White` circle whose diameter is `12.0 / (M + 2)`,
    * where `M` is the star’s magnitude. Its position within the resulting image is
    * determined by [[Star.posIn]]. The given star must have a magnitude greater than -2.
    *
    * For example, say the background image is 400 by 400 pixels, and the given star has
    * the coords (0.5,0.0) and a magnitude of -0.5. The returned image will then consist of
    * the given background image with white circle of radius 4 placed upon it at (300,200).
    *
    * @param skyPic  an image to place the star upon
    * @param star    a star (of magnitude > -2) that should be depicted against the given image */
  def placeStar(skyPic: Pic, star: Star) = {
    val starSize = 12.0 / (star.magnitude + 2)
    val starPic = circle(starSize, White)
    skyPic.place(starPic, star.posIn(skyPic))
  }


  /** Given an image of the sky and a star, returns a version of the original image with
    * a constellation drawn on top.
    *
    * A constellation is depicted using a combination of red circles and yellow lines.
    * At each star that belongs to the constellation, a red circle with a diameter of 4 pixels
    * is added. A yellow line is then drawn between each pair of connected stars. The
    * positions of the stars within the image are determined as in [[placeStar]]. */
  def placeConstellation(skyPic: Pic, constellation: Constellation) = {
    val withDots = constellation.stars.foldLeft(skyPic)(placeDot)
    constellation.lines.foldLeft(withDots)(placeLine)
  }

  private def placeLine(skyPic: Pic, starPair: (Star, Star)) = { // assumes dark, square skyPic
    val (from, to) = (starPair._2.posIn(skyPic), starPair._1.posIn(skyPic))
    skyPic.place(line(from, to, Yellow), from)
  }

  private def placeDot(skyPic: Pic, star: Star) = skyPic.place(circle(4, Red), star.posIn(skyPic))




}
