package o1.auctionhouse


/** Each instance of the class `FixedPriceSale` represents an item that has been put
  * up for sale in an (imaginary) electronic auction house. Each such item is being
  * sold for a fixed price (as opposed to being auctioned).
  *
  * Apart from a price, each sale has a duration measured in days. Every day, the method
  * `advanceOneDay` must be called. If the set number of days is reached without anyone
  * buying the item, the sale expires and the item can no longer be bought.
  *
  * @param description  a short description of the item
  * @param price        the price the item is being sold for
  * @param duration     the maximum number of days the sale will remain open */
class FixedPriceSale(description: String, val price: Int, duration: Int) extends InstantPurchase(description) {

  private var remaining = duration                 // stepper


  /** Returns the number of days remaining until the sale expires, unless someone
    * buys the item. Initially, this number will be equal to the sale's duration, but
    * will decrease with each passing day until it reaches zero or the item is bought.
    *
    * If the item has been already bought when this method is called, the number
    * of days that were remaining at the time of purchase is returned. A return value
    * of zero means that time has already run out to buy the item. */
  def daysLeft = this.remaining


  /** Determines whether the sale has expired. That is, determines whether the item
    * is no longer available because time has run out. Note that if the item is
    * unavailable because it was bought, it has not "expired" in this sense and
    * this method will return `false`. */
  def isExpired = this.remaining <= 0


  /** Records one day as having passed. In practice, what this means is that the
    * item becomes one day closer to expiring unless it has already been bought. */
  def advanceOneDay() = {
    if (this.isOpen) {
      this.remaining = this.remaining - 1
    }
  }

}