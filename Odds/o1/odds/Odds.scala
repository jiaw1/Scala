package o1.odds

// This class is gradually developed between Chapters 2.4 and 3.4.

class Odds(val wont: Int, val will: Int) {


  // Implemented in Chapter 2.4:

  def probability = 1.0 * this.will / (this.wont + this.will)

  def fractional = s"$wont/$will"

  def decimal = 1 / this.probability

  def winnings(bet: Double) = bet * this.decimal


  // Implemented in Chapter 2.5:

  override def toString = this.fractional

  def not = new Odds(this.will, this.wont)

  def both(other: Odds) =   new Odds(this.wont * other.wont  +  this.wont * other.will  +  this.will * other.wont,
                                     this.will * other.will)

  def either(other: Odds) = new Odds(this.wont * other.wont,
                                     this.wont * other.will  +  this.will * other.wont  +  this.will * other.will)


  // Implemented in Chapter 3.3:

  def isLikely = this.will > this.wont

  def isLikelierThan(other: Odds) = this.probability > other.probability


  // Implemented in Chapter 3.4:

  def moneyline =
    if (this.isLikely)
      -100 * this.will / this.wont
    else
       100 * this.wont / this.will


  // Implemented in Chapter 3.6:

  def eventHappens() = {
    import scala.util.Random
    Random.nextInt(this.wont + this.will) >= this.wont
  }
}
