package o1.people

/** Each instance of class `Member` represents an individual person, recorded by an
  * (imaginary) society-membership app. A member has a number of basic attributes such
  * as a name and a year of birth.
  *
  * Once created, a `Member` object is immutable; to represent changes in a person,
  * one should create a new instance of the class.
  *
  * @param id           a number that uniquely identifies the member from other members of the society
  * @param name         the member's name
  * @param yearOfBirth  the year the member was born
  * @param yearOfDeath  the year the member died (wrapped in an `Option`; `None` if the member is alive) */
class Member(val id: Int, val name: String, val yearOfBirth: Int, val yearOfDeath: Option[Int]) {

  /** Returns a boolean value indicating whether the member is alive or not. That is,
    * returns `true` if the member does not have a year of death,* and `false` if they do. */
  def isAlive = this.yearOfDeath.isEmpty


  /** Returns a compact string description of the member. The description of a dead person has
    * the form `"Name(YoB-YoD)"; e.g. `"Elvis(1935-1977)"`. The description of a living person is
    * the same but with no year of death; e.g., `"Madonna(1958-)"`. */
  override def toString = this.name + "(" + this.yearOfBirth + "-" + this.yearOfDeath.getOrElse("") + ")"

}
