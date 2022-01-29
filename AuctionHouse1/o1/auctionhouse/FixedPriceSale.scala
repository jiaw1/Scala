package o1.auctionhouse

/** Each instance of the class `FixedPriceSale` represents an item that has been put
  * up for sale in an (imaginary) electronic auction house. Each such item is being
  * sold for a fixed price (as opposed to being auctioned).
  *
  * Apart from a price, each sale has a duration measured in days. Every day, the method
  * `advanceOneDay` must be called. If the set number of days is reached without anyone
  * buying the item, the sale expires and the item can no longer be bought.
  *
  * This simple class does not store any seller information.
  *
  * @param description  a short description of the item
  * @param price        the price the item is being sold for
  * @param duration     the maximum number of days the sale will remain open */
class FixedPriceSale(val description: String, val price: Int, duration: Int) {

  private var remaining = duration                 // stepper
  private var buyerName: Option[String] = None     // fixed value (once set)


  /** Returns the number of days remaining until the sale expires, unless someone
    * buys the item. Initially, this number will be equal to the sale's duration, but
    * will decrease with each passing day until it reaches zero or the item is bought.
    *
    * If the item has been already bought when this method is called, the number
    * of days that were remaining at the time of purchase is returned. A return value
    * of zero means that time has already run out to buy the item. */
  def daysLeft = this.remaining


  /** Returns the buyer of the item, wrapped in an `Option`;
    * `None` is returned if nobody has bought the item yet. */
  def buyer = this.buyerName


  /** Determines if the sale is open, that is, if the item can still be bought. A fixed-price
    * sale is always open if nobody has yet bought the item and the sale has not yet expired. */
  def isOpen = !this.isExpired && this.buyer.isEmpty


  /** Determines whether the sale has expired. That is, determines whether the item
    * is no longer available because time has run out. Note that if the item is
    * unavailable because it was bought, it has not "expired" in this sense and
    * this method will return `false`. */
  def isExpired = this.remaining <= 0


  /** Records one day as having passed. In practice, what this means is that the
    * item becomes one day closer to expiring, unless it had already been bought
    * in which case its state does not change. */
  def advanceOneDay() = {
    if (this.isOpen) {
      this.remaining = this.remaining - 1
    }
  }


  /** Buys the item for the given customer. The sale now has a buyer (and is therefore no
    * longer open). This only works, however, if the sale was open to begin with. The method
    * returns `true` if the item was successfully bought, `false` if the purchase failed. */
  def buy(buyer: String) = {
    if (this.isOpen) {
      this.buyerName = Some(buyer)
      true
    } else {
      false
    }
  }


  /** Returns a textual description of the item. This text is the same as that returned by `description`. */
  override def toString = this.description

}


