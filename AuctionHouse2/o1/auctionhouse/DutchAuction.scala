package o1.auctionhouse

import scala.math._

/** Each instance of the class `DutchAuction` represents an item that has been
  * put up for sale in an electronic auction house. More specifically, the item
  * is being sold in a so-called Dutch auction.
  *
  * Each Dutch auction has a starting price for the item being sold. However,
  * unlike in a typical ("English-style") auction where the price goes up from
  * the starting price, the price in a Dutch auction goes down until someone
  * decides to purchase the item. Every day, the price of the item is decreased
  * by a certain amount from the previous day's price, and the first person who
  * decides to buy the item gets it immediately at the current price.
  *
  * Upon creation, a starting price and a minimum price are set for the auction.
  * Every day, the method `advanceOneDay` must be called to update the auction
  * status and to decrease the current price if nobody has bought the item yet.
  * The price of the item will never drop below the preset minimum price. After the
  * minimum price is reached, the auction will remain open (at the minimum price)
  * for a further three days. If nobody still buys the item, the auction expires.
  *
  * To summarize, a Dutch auction can be in any of the four stages described below.
  *
  * ''Decreasing price'': The auction is open and the current price
  * of the auction keeps dropping daily until the minimum price is
  * reached or until someone buys the item.
  *
  * ''Stagnant'': The auction is still open but its price no longer
  * decreases, and instead stays at the minimum price.
  *
  * ''Expired'': After the price of the auction has been stagnant at
  * the minimum price for over three days (that is, three additional
  * full days after reaching the minimum price), and still nobody has
  * bought the item, the auction expires and is no longer open.
  *
  * ''Bought'': As soon as anyone chooses to buy the item, the auction
  * ceases to be open.
  *
  * Please note that the term "Dutch auction" has various meanings, one of which
  * is used here. The kind of Dutch auctions that this class represents, are not
  * identical to those featured on the trading web site eBay, for instance.
  *
  * @param description    a short description of the item
  * @param startingPrice  the initial price of the item
  * @param decrement      the amount that is removed from the current price each day
  * @param minimumPrice   the lowest possible price that the seller is willing to accept */
class DutchAuction(description: String,
                   val startingPrice: Int,
                   val decrement: Int,
                   val minimumPrice: Int) extends InstantPurchase(description) {

  private var reducingPrice = this.startingPrice   // stepper: downwards from starting price to minimumPrice
  private var stagnantDays = 0                     // stepper: 0, 1, 2, ...


  /** Returns the current price of the item, that is, the price that the item can be
    * purchased for at the moment, assuming the auction is open. If the auction is not
    * open, the actual sale price (if bought) or the minimum price (is expired) is returned.*/
  def price = this.reducingPrice


  /** Returns the "price ratio" of the auction that describes the current "cheapness" of
    * the item; the lower, the cheaper. The price ratio is the ratio of the current price
    * to the starting price. For instance, if the current price is 500 and the starting
    * price is 1000, the price ratio is 0.5. */
  def priceRatio = this.price.toDouble / this.startingPrice


  /** Records one day as having passed. For a Dutch auction, this means that if the auction
    * is still open, the current price of the auction is reduced by the preset decrement,
    * but only until the minimum price of the auction. (If the price would go lower than
    * the minimum, it only reaches the minimum instead.)
    *
    * If the auction was already at the minimum price to begin with, then the current price
    * does not decrease at all; instead the auction object keeps track of how many days
    * the price has remained stagnant at the minimum level. This will eventually lead to
    * auction expiry when this method is called for the fourth time after the current price
    * reached the minimum price.
    *
    * @see [[isExpired]]
    * @see [[isOpen]] */
  def advanceOneDay() = {
    if (this.isOpen) {
      if (this.reducingPrice > this.minimumPrice) {
        this.reducingPrice = max(this.minimumPrice, this.reducingPrice - this.decrement)
      } else {
        this.stagnantDays += 1
      }
    }
  }


  /** Determines if the auction has expired, that is, if it has ended without the item
    * being sold. A Dutch auction expires if the price remains stagnant at the minimum price
    * level for over three days without anyone buying the item (not counting the day when the
    * price originally reached the minimum price).
    *
    * (Note: Being expired implies that the auction is also closed. However, not being
    * expired does not guarantee that the auction is open. An auction closes when either
    * it expires or someone buys the item.)
    *
    * @see [[advanceOneDay]]
    * @see [[isOpen]] */
  def isExpired = {
    val StagnationLimit = 3
    this.stagnantDays > StagnationLimit
  }


}

