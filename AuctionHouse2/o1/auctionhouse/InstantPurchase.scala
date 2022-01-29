package o1.auctionhouse

/** The class `InstantPurchase` represents items that have been put up for sale
  * in an electronic auction house in such a way that they may be immediately
  * purchased at any time while the item is open. As soon as anyone purchases the
  * item, the sale is finalized and nobody else can buy the item. */
abstract class InstantPurchase(description: String) extends ItemForSale(description) {

  private var buyerName: Option[String] = None     // fixed value (once set)


  /** Returns the buyer of the item, wrapped in an `Option`;
    * `None` is returned if nobody has bought the item yet. */
  def buyer = this.buyerName


  /** Determines if the sale is open, that is, if the item can still be bought.
    * An item like this is always open if nobody has yet bought it and the
    * opportunity to buy it has not yet expired. */
  def isOpen = !this.isExpired && this.buyer.isEmpty


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

}