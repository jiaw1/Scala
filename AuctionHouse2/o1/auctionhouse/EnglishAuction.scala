package o1.auctionhouse

import scala.math._

/** The class EnglishAuction represents "normal", "English-style" auctions in
  * an electronic auction house. Each auction object represents an auction for
  * a single item that has been put up for sale.
  *
  * Each English auction has a starting price for the item being sold. No bids
  * lower than the starting price are accepted. The highest bidder wins the
  * item at the end of the auction. If two bidders bid the same amount, the
  * first bidder wins over the later one. Apart from this, the order in which
  * bids are placed is not significant.
  *
  * Upon creation, a duration (in days) is set for the auction. Every day,
  * the method `advanceOneDay` must be called to update the auction status.
  * Once the time limit is reached, the auction closes and the highest bid
  * wins. If there were no bids made by this time, the auction closes anyway
  * and is considered "expired".
  *
  * In a traditional non-electronic auction, all bidders are physically present
  * and it is very common for the same person to make multiple slightly
  * increased bids during the course of the auction, which only lasts a
  * relatively short time. The bidding system in an electronic "English auction"
  * is different. When bidding, bidders indicate the ''maximum'' price that
  * they are willing to pay for the item. Storing the maximum bid amount in the
  * electronic auction house is convenient, as it allows bidders to indicate
  * how far they are willing to go, without having to come back to the auction
  * house service repeatedly to increase their bids.
  *
  * Even if a bidder later wins the auction, they do not necessarily have to
  * pay the full maximum price indicated when placing the bid. They only
  * need to pay enough to beat the second highest bidder. Consider an example.
  * Say bidder A has bid a maximum of 100 for an item and bidder B has bid 150,
  * and the auction then closes. B wins the item and has to pay a price of
  * 101 for it, just enough to beat A's 100.
  *
  * The maximum bid amount of the leading bidder is not publicly displayed.
  * The program is intended to display only the current price of the auction
  * and the minimum amount that new bidders must bid in order to stand any
  * chance of winning. For instance, say bidder A has placed a bid with a
  * limit of 200 for an item, and bidder B has placed a bid with a limit of 300.
  * The current price of the auction is now 201. The minimum amount that new
  * bidders must bid is 202 (good enough to beat 201). B's limit of 300
  * will not become apparent to other bidders unless someone bids over 300,
  * thereby becoming the leading bidder. (Not displaying the leading bidder's
  * limit is a sensible policy. Otherwise, bidders or sellers could raise the
  * price without any intention of actually winning the item.)
  *
  * See [[http://www.huuto.net/ohjeet/ohje-selailu#automaatti Huuto.Net]]
  * or [[http://pages.ebay.com/help/buy/automatic-bidding.html eBay]]
  * for descriptions of similar real-world systems.
  *
  * This simple class does not support bid cancellations, reserve prices, item
  * buyouts, etc., and does not store any seller information.
  *
  * @param description    a short description of the item
  * @param startingPrice  the initial price of the item, that is, the lowest possible price
  *                       the seller is willing to accept
  * @param duration       the length of the auction, in days
  *
  * @see [[Bid]] */
class EnglishAuction(description: String, val startingPrice: Int, duration: Int) extends ItemForSale(description) {

  private var highest = new Bid(None, startingPrice)       // most-wanted holder
  private var secondHighest = new Bid(None, startingPrice) // most-wanted holder
  private var remainingDays = duration                     // stepper


  /** Returns the number of days left before the auction closes. A return value of
    * zero indicates that time has run out and the auction has closed. */
  def daysLeft = this.remainingDays


  /** Records one day as having passed. For an English auction, this simply means that
    * if the auction is still open, its remaining duration is shortened by one day. */
  def advanceOneDay() = {
    if (this.isOpen) {
      this.remainingDays -= 1
    }
  }


  /** Determines if the auction is open, that is, if the item can still be bought. An
    * English auction is always open if its allocated duration has not been reached yet. */
  def isOpen = this.remainingDays > 0


  /** Determines whether not a single bid has been placed yet. */
  def hasNoBids = this.highest.isInitialBid


  /** Determines if the auction has expired, that is, if it has ended without the item
    * being sold. An English auction expires if the allocated duration has passed and no
    * bids have been made. */
  def isExpired = !this.isOpen && this.hasNoBids


  /** Returns the winner of the auction, or the current highest bidder if the auction has
    * not closed yet. The person's name is wrapped in an `Option`; `None` is returned if
    * no bids have been made. */
  def buyer = this.highest.bidder


  /** Returns the price the item is currently selling for, that is, the money that the
    * currently winning bidder would have to pay if the auction were to close now (or
    * must pay if the auction is already closed).
    *
    * If there are no bids or there is just a single bid on the item, the current price
    * equals the starting price of the item. If there is more than one bid, then the
    * current price is the price that is just enough for the highest bidder to beat the
    * second highest bidder (as described in the introduction to this class).
    *
    * Note that if the two highest bids are equal, the first bidder always wins over the
    * second. The first bidder does not have to pay over their bid limit simply because
    * someone else also bid the same amount. */
   def price =
      if (this.secondHighest.isInitialBid)
        this.startingPrice
      else
        min(this.secondHighest.limit + 1, this.highest.limit)


  /** Returns the lowest amount that a would-be bidder will have to bid for this item so
    * as to stand a chance of winning the auction. If there are no bids on the item yet,
    * the minimum bid amount equals the starting price of the auction. Otherwise, the bid
    * amount must be higher than the current price of the auction. (See the introduction
    * to this class for an example.)
    * @see [[price]] */
  def requiredBid = if (this.hasNoBids) this.startingPrice else this.price + 1


  /** Places a bid on the item, assuming that the auction is open and the bid is sufficiently
    * high. If those conditions are not met, this method has no effect.
    * @param bidder  the name of the bidder
    * @param amount  the maximum amount the bidder is willing to pay for the item
    * @return a boolean value indicating whether the new bid is now the leading bid for the item */
  def bid(bidder: String, amount: Int) = {
    val newBid = new Bid(Some(bidder), amount)
    if (this.isOpen && amount >= this.requiredBid) {
      this.secondHighest = if (newBid.beats(this.highest)) this.highest else newBid.winner(this.secondHighest)
      this.highest = newBid.winner(this.highest)
    }
    this.highest == newBid
  }


}
