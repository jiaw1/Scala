package o1.blood

// This code is introduced in Chapter 7.2.

sealed trait Rhesus {
  val isPositive: Boolean
  def isNegative = !this.isPositive
  def canDonateTo(recipient: Rhesus) = this.isNegative || this == recipient
  def canReceiveFrom(donor: Rhesus) = donor.canDonateTo(this)
}

object RhPlus extends Rhesus {
  val isPositive = true
  override def toString = "+"
}

object RhMinus extends Rhesus {
  val isPositive = false
  override def toString = "-"
}


sealed trait ABO {
  val antigens: String
  def canDonateTo(recipient: ABO) = this.antigens.forall( recipient.antigens.contains(_) )
  def canReceiveFrom(donor: ABO) = donor.canDonateTo(this)
  override def toString = this.antigens
}

object A extends ABO {
  val antigens = "A"
}

object B extends ABO {
  val antigens = "B"
}

object AB extends ABO {
  val antigens = "AB"
}

object O extends ABO {
  val antigens = ""
  override def toString = "O"
}



class ABORh(val abo: ABO, val rhesus: Rhesus) {
  def canDonateTo(recipient: ABORh) = this.abo.canDonateTo(recipient.abo) && this.rhesus.canDonateTo(recipient.rhesus)
  def canReceiveFrom(donor: ABORh) = donor.canDonateTo(this)
  override def toString = this.abo.toString + this.rhesus.toString
}




object solutionForAdvancedAssignment {

  trait BloodType[ThisSystem <: BloodType[ThisSystem]] {
    this: ThisSystem =>
    def canDonateTo(recipient: ThisSystem): Boolean
    def canReceiveFrom(donor: ThisSystem) = donor.canDonateTo(this)
  }

  sealed trait Rhesus extends BloodType[Rhesus] {
    val isPositive: Boolean
    def isNegative = !this.isPositive
    def canDonateTo(recipient: Rhesus) = this.isNegative || this == recipient
  }
  object RhPlus extends Rhesus {
    val isPositive = true
    override def toString = "+"
  }
  object RhMinus extends Rhesus {
    val isPositive = false
    override def toString = "-"
  }

  sealed trait ABO extends BloodType[ABO] {
    val antigens: String
    def canDonateTo(recipient: ABO) = this.antigens.forall( recipient.antigens.contains(_) )
    override def toString = this.antigens
  }
  object A extends ABO {
    val antigens = "A"
  }
  object B extends ABO {
    val antigens = "B"
  }
  object AB extends ABO {
    val antigens = "AB"
  }
  object O extends ABO {
    val antigens = ""
    override def toString = "O"
  }


  class ABORh(val abo: ABO, val rhesus: Rhesus) extends BloodType[ABORh] {
    def canDonateTo(recipient: ABORh) = this.abo.canDonateTo(recipient.abo) && this.rhesus.canDonateTo(recipient.rhesus)
    override def toString = this.abo.toString + this.rhesus.toString
  }


}