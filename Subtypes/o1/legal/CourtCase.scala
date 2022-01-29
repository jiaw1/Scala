package o1.legal


/** Each instance of this class represents a single court case in which one
  * [[Entity]] is the plaintiff and another is the defendant.
  *
  * This toy class represents no other information about the court case beyond
  * the two legal entities involved.
  *
  * @param plaintiff  the initiator of the court case
  * @param defendant  the entity that responds against an accusation in the case */
class CourtCase(val plaintiff: Entity, val defendant: Entity) {

  /** Describes the court case. The description is a string of the form "PLAINTIFF v. DEFENDANT",
    * using the `name` of the two parties. E.g., "Jane Doe v. ACME Corp". */
  override def toString = this.plaintiff.name + " v. " + this.defendant.name

}

