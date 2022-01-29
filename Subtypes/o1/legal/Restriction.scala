package o1.legal


/** A `Restriction` is a reason why a [[ReducedCapacityPerson]] cannot function independently as a legal entity. */
abstract class Restriction(val description: String) {
  override def toString = s"capacity reduced due to $description"
}


/** This singleton object represents the [[Restriction]] of being mentally or physically unable to
  * function independently as a legal [[Entity]]. It has the `description` "mental and/or physical condition". */
object Illness extends Restriction("mental and/or physical condition")


/** This singleton object represents the [[Restriction]] of being too young to function
  * independently as a legal [[Entity]]. It has the `description` "age". */
object Underage extends Restriction("age")


