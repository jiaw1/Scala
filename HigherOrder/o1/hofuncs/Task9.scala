package o1.hofuncs

// This program is introduced in Chapter 6.1.

import o1._

object Task9 extends App {

  // PHASE 1/2:
  // Implement turnElementsIntoResult so that it computes its return value by
  // applying a given function (its third parameter) to the numbers contained in
  // a given vector (its first parameter). The second parameter indicates the
  // base value of the computation (which gets returned if the vector is empty).
  //
  // For example, say "numbers" contains the integers 20, 10, and 5, "initialValue"
  // equals 0 and "operation" is a function that returns the sum of its two parameters.
  //
  //   1. The "operation" is first applied to "initialValue" and
  //      the first number in the vector. 0 + 20 equals 20.
  //   2. The "operation" is then applied to the previous result
  //      and the next element. 20 + 10 = 30.
  //   3. The operation is again applied to the previous result
  //      and the next element. 30 + 5 = 35.
  //   4. The last element has been processed, so it's time to
  //      stop and return 35.
  //
  // As you will have noticed, this example demonstrates one way of summing up
  // the elements of a vector.
  def turnElementsIntoResult(numbers: Vector[Int], initialValue: Int, operation: (Int, Int) => Int) = {
    var result = initialValue               // result is a gatherer
    for (number <- numbers) {
      result = operation(result, number)
    }
    result
  }


  val exampleNumbers = Vector(100, 25, -12, 0, 50, 0)
  println("The numbers are: " + exampleNumbers.mkString(", "))

  def addToSum(oldSum: Int, nextNumber: Int) = oldSum + nextNumber

  println("Sum: " + turnElementsIntoResult(exampleNumbers, 0, addToSum))


  // PHASE 2/2:
  // Use turnElementsIntoResult to compute:
  //   1) the sum of the absolute values of each number in "exampleNumbers";
  //   2) how many positive numbers there are among "exampleNumbers";
  //   3) the product of all the other "exampleNumbers" except for zeros.
  // To that end, flesh out the three helper functions below and replace the
  // zero results with calls to turnElementsIntoResult. You'll need to design
  // the helper functions yourself so that they work in combination with
  // turnElementsIntoResult to produce the desired results.

  def addAbsolute(oldSum: Int, nextNumber: Int) =
    oldSum + nextNumber.abs

  def positiveCount(countSoFar: Int, nextNumber: Int) =
    countSoFar + (if (nextNumber > 0) 1 else 0)

  def productOfNonZeros(productSoFar: Int, nextNumber: Int) =
    if (nextNumber == 0) productSoFar else productSoFar * nextNumber

  val sumOfAbsolutes    = turnElementsIntoResult(exampleNumbers, 0, addAbsolute)
  val howManyPositives  = turnElementsIntoResult(exampleNumbers, 0, positiveCount)
  val product           = turnElementsIntoResult(exampleNumbers, 1, productOfNonZeros)
  println("Sum of absolute values: " + sumOfAbsolutes)
  println("Number of positive elements: " + howManyPositives)
  println("Product: " + product)


}

