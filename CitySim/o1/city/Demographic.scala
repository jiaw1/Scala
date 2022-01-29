package o1.city

import o1.Color


/** A `Demographic` indicates which demographic ("slice of the overall population")
  * a particular household belongs to (if any). It is used by the simulator
  * to mark households on a city map.
  *
  * There are two kinds of `Demographic` objects: `Vacant` and `Occupied`:
  *
  *  - The singleton object [[Vacant]] is used for indicating that
  *    a residence is empty and does not belong to any demographic.
  *  - [[Occupied]] objects indicate that a residence belongs to a
  *    specific demographic; each different demographic is assigned
  *    a different `Color`. For instance, a residence may be "occupied
  *    by the red demographic", or "occupied by the blue demographic".
  *
  * What exactly constitutes a demographic is unimportant for the purposes of this
  * class. Demographics could be based on people's socioeconomic status, political
  * views, ethnicity, age, or something else.
  *
  * All `Demographic` objects are immutable.
  *
  * The trait `Demographic` itself does not define any methods; it merely serves
  * as a supertype for [[Occupied]] and [[Vacant]]. */
sealed trait Demographic


/** An `Occupied` object signals that a household belongs to a demographic
  * specific by the color label stored within the `Occupied` object.
  * For instance, a residence may be "occupied by the red demographic",
  * or "occupied by the blue demographic".
  *
  * An `Occupied` object is immutable
  *
  * @param label  a color that distinguishes the occupying demographic from others */
final class Occupied(val label: Color) extends Demographic {
  override def toString = s"occupied by the $label demographic"
}


/** This immutable singleton object effectively means "no demographic at all".
  * It can be used on a [[CityMap]] to indicate that a residence is empty. */
object Vacant extends Demographic {
  override def toString = "vacant residence"
}

