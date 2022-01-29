package o1.people

/** Each instance of class `Passenger` represents an individual passenger in an
  * imaginary traffic-related application.
  *
  * @param name  the passenger's name
  * @param card  a travel card associated with the person, if there is one; `None` indicates that there isn't */
class Passenger(val name: String, val card: Option[TravelCard]) {

  /** Determines whether a valid travel card is associated with the person or not. That is,
    * the person must have a travel card and the card's `isValid` method must return `true`. */
  def canTravel = this.card match {
    case Some(actualCard) => actualCard.isValid
    case None             => false
  }


  /** Determines whether the person is associated with a travel card or not. As long as
    * the person has a card, the method returns `true` even if the card is invalid. */
  def hasCard = this.card.isDefined

}
