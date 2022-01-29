package o1
package object flappy { // These definitions at the top are discussed in Chapter 5.2.

  // This file defines constants that are used in o1.flappy to define the rules of FlappyBug.

  val ObstacleSpeed = 10
  val BugSpeed = 2
  val Gravity = 2
  val BugRadius = 15
  val FlapStrength = 15

  val ViewWidth = 1000
  val ViewHeight = 400
  val GroundDepth = 50
  val GroundY = ViewHeight - GroundDepth

}