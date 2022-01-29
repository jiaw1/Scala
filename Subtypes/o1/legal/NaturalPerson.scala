package o1.legal


/** A `NaturalPerson` is a legal entity that is a single human being.
  * In addition to a name, which every [[Entity]] has, a `NaturalPerson`
  * has a `personID`.
  *
  * @param personID  an identifier that uniquely specifies the individual person, such as a social security number
  * @param name      the person's name */
abstract class NaturalPerson(val personID: String, name: String) extends Entity(name) {

  /** A short description of the entity’s type. For natural persons, this method returns
    * the word "human" by default, although this behavior may be overridden by subclasses. */
  def kind = "human"
}



/** A `FullCapacityPerson` is a `NaturalPerson` that has no restrictions on their
  * ability to function as a legal entity (due to being underage, indisposed, etc.).
  *
  * @param personID  an identifier that uniquely specifies the individual person, such as a social security number
  * @param name      the person's name */
class FullCapacityPerson(personID: String, name: String) extends NaturalPerson(personID, name) {

  /** The contact person of the legal entity. A `FullCapacityPerson` functions as their own contact. */
  def contact = this


  /** A short description of the entity’s type. For a `FullCapacityPerson`, this method returns
    * the default `kind` defined for `NaturalPerson`, followed by the string " in full capacity". */
  override def kind = super.kind + " in full capacity"

}



/** A `ReducedCapacityPerson` is a `NaturalPerson` that has a [[Restriction]] on their
  * ability to function independently as a legal entity (age, illness, etc.). As a consequence,
  * they also have a `FullCapacityPerson` who serves as their guardian and representative.
  *
  * @param personID     an identifier that uniquely specifies the individual person, such as a social security number
  * @param name         the person's name
  * @param restriction  the reason why the person has reduced legal capacity
  * @param guardian     the person who represents the reduced-capacity person */
class ReducedCapacityPerson(personID: String, name: String, val restriction: Restriction, val guardian: FullCapacityPerson) extends NaturalPerson(personID, name) {

  /** The contact person of the legal entity. The contact of a `ReducedCapacityPerson` is their guardian. */
  def contact = this.guardian

  /** A short description of the entity’s type. For a `ReducedCapacityPerson`, this method
    * returns the default `kind` defined for `NaturalPerson`, followed by the string " with "
    * and further followed by the `toString` description of the person's `restriction`.
    * E.g., "human with capacity reduced due to age". */
  override def kind = super.kind + " with " + this.restriction

}

