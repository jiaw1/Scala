package o1.money


class Currency(val coinValues: Vector[Int]) {

  def numberOfCoinTypes = this.coinValues.size

  def waysToSplit(sum: Int) = {
    def ways(sum: Int, values: Vector[Int]): Int =
      if (sum < 0) 0
      else if (sum == 0) 1
      else if (values.isEmpty) 0
      else ways(sum, values.tail) + ways(sum - values.head, values)
    ways(sum, this.coinValues)
  }

  override def toString = this.coinValues.mkString(",")

}


object Currency {

  val EUR = new Currency(Vector(1, 2, 5, 10, 20, 50, 100, 200))
  val USD = new Currency(Vector(1, 5, 10, 25, 50))

}