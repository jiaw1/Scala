package o1.carsim
import o1.Pos
import scala.math.min

/** The class `Car` represents virtual cars which can drive about on a surface.
  * A car has a fuel tank that contains gasoline, which is spent as the car moves
  * but can be added using an appropriate method.
  *
  * Although some aspects of a car object are immutable (e.g., fuel consumption rate),
  * some others change (e.g., location, fuel).
  *
  * @param fuelConsumption  the fuel consumption of the new car '''in liters per 100 km'''
  * @param tankSize         the size of the new car's fuel tank, in liters
  * @param initialFuel      the initial amount of fuel in the car's tank, in liters (at most equal to tank size)
  * @param initialLocation  the starting location of the car */
class Car(val fuelConsumption: Double, val tankSize: Double, initialFuel: Double, initialLocation: Pos) {

  private var driven = 0.0                        // gatherer
  private var fuelInTank = initialFuel            // gatherer
  private var currentLocation = initialLocation   // gatherer


  /** Adds a given amount of fuel to the tank, if possible. If the given amount cannot be
    * poured into the tank, the tank is filled up and the rest of the fuel discarded.
    * @param toBeAdded  the number of liters of fuel to be placed in the tank
    * @return the number of liters of fuel successfully placed in the tank */
  def fuel(toBeAdded: Double) = {
    val oldAmount = this.fuelInTank
    this.fuelInTank = min(this.tankSize, oldAmount + toBeAdded)
    this.fuelInTank - oldAmount
  }


  /** Fills the tank of this car to capacity. Returns the number of liters
    * of fuel that were successfully placed in the tank. */
  def fuel(): Double = {
    this.fuel(this.tankSize)
  }


  /** Returns a number between 0.0 and 100.0 that represents the amount of fuel in the tank
    * as a percentage. That is, 100.0 means the tank is full and 0.0 means it is empty. */
  def fuelRatio = this.fuelInTank / this.tankSize * 100


  /** Returns the current location of the car. */
  def location = this.currentLocation


  /** Returns the number of '''meters''' the car has been driven, in total, since it was created. */
  def metersDriven = this.driven


  /** Returns the distance in that the car can drive with the remaining amount of fuel.
    * Note that this method returns the distance in meters even though `fuelConsumption`
    * is recorded as liters per 100 km. */
  def fuelRange = this.fuelInTank / this.fuelConsumption * 100000


  /** Drives the car toward the given destination. This causes the car’s location and its
    * fuel level to change.
    *
    * Assuming the car has enough fuel to reach the destination, it moves there. (In
    * effect, the car changes location just once and “teleports” to the destination,
    * with no intermediate steps.)
    *
    * If fuel runs out, the car stops along the way, and its new location is determined by finding the
    * location along the route where the car no longer has the fuel to move further. An example: A car
    * drives from (61.0, 25.0) towards the destination (62.0, 25.5), but only has fuel for 60% of the trip.
    * Consequently, it stops at (61.6, 25.3).
    *
    * For purposes of determining the car’s stopping point (if fuel runs out) and the amount of
    * fuel consumed by the drive, the car is assumed to move in a straight line “as the crow flies”
    * at a constant speed and to consume fuel evenly throughout.
    *
    * '''Note:''' As the example above illustrates, this method assumes that the car and its destination
    * exist not on a sphere (or geoid) but on a flat, two-dimensional surface whose x and y coordinates
    * map directly to longitude and latitude. The method relies on the assumption that the driven distance
    * is very short compared to the size of Earth, which allows this simplification.
    *
    * '''Also note:''' This method receives, as a parameter, the overall distance from the starting
    * location to the intended destination. The method relies on this information rather than attempting
    * to compute the number of meters between these two points.
    *
    * @param destination          the intended destination of the car
    * @param metersToDestination  the distance, in '''meters''', from the car's current location to `destination` */
  def drive(destination: Pos, metersToDestination: Double) = {
    if (metersToDestination > 0) {
      val actuallyDriven = min(metersToDestination, this.fuelRange)
      this.driven += actuallyDriven
      this.consumeFuel(actuallyDriven)
      val rangeRatio = actuallyDriven / metersToDestination
      this.currentLocation += this.currentLocation.vectorTo(destination).multiply(rangeRatio)
      // A couple of the many other possible implementations for the last line above:
      //
      // val myLoc = this.currentLocation
      // this.currentLocation = myLoc.add(rangeRatio * myLoc.xDiff(destination),
      //                                  rangeRatio * myLoc.yDiff(destination))
      //
      // this.currentLocation += (destination - this.currentLocation) * rangeRatio
    }
  }


  private def consumeFuel(meters: Double) = {
    this.fuelInTank -= meters * (this.fuelConsumption / 100000)
  }


}

