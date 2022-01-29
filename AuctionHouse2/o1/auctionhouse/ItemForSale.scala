package o1.auctionhouse

/** The class `ItemForSale` represents items that have been put up for sale in an (imaginary)
  * electronic auction house. Items can be sold in various ways (fixed price, auctions, etc.);
  * the concrete subclasses of this class represent those different ways. This abstract class
  * captures some of the common characteristics of all different sales.
  *
  * This simple implementation does not store any seller information.
  *
  * @param description  a short description of the item */
abstract class ItemForSale(val description: String) {

  /** Returns the current price of the item, that is, the price that the item
    * is selling for at the moment. If the item is not open, the sale price
    * (if bought) or closing price (if expired) is returned. */
  def price: Int


  /** Returns the winner of the item, or the person who is currently leading the bidding,
    * in the case of an auction that is still open. The person's name is returned in an
    * `Option` wrapper; `None` is returned if nobody has expressed an interest in buying the item. */
  def buyer: Option[String]


  /** Determines if the item is open, that is, if it can still be bought. Whether this
    * is the case depends on the way the item is sold. */
  def isOpen: Boolean


  /** Determines if the item has expired, that is, if the sale has ended due to a lack of
    * interest and without the item having been sold. How soon this happens depends on
    * the way the item is sold. */
  def isExpired: Boolean


  /** Records one day as having passed. The effects of this depend on the way the item is sold. */
  def advanceOneDay(): Unit


  /** Returns a textual description of the item. This text is the same as that returned by `description`. */
  override def toString = this.description

}