package o1.legal


/** An `Entity` object represents a legal entity. A legal entity is essentially something
  * that can sue or be sued in court, such as a person, a corporation, or a sovereign nation.
  * That is, an `Entity` can be the plaintiff of the defendant in a [[CourtCase]]. (This is an
  * approximation of [[https://simple.wikipedia.org/wiki/Legal_entity the real-world concept]].)
  *
  * There are different kinds of legal entities; the abstract superclass `Entity` describes
  * what is common to them all: they have a name, a contact person, and a `kind`, which is
  * a textual description of the more specific type of the entity.
  *
  * @param name  the name of the legal entity, such as "Jane Doe" or "ACME Inc." */
abstract class Entity(val name: String) {

  /** The contact person of the legal entity. This is another legal entity which must be a human (a [[NaturalPerson]]). */
  def contact: NaturalPerson

  /** A short description of the type of the legal entity. E.g., "human in full capacity" or "for-profit corporation". */
  def kind: String

  /** Returns a textual description of the legal entity. This takes the form "NAME (KIND)",
    * e.g., "Jane Doe (human in full capacity)". */
  override def toString = s"$name ($kind)"

}

