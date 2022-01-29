
////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it's not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

package o1.robots.gui


import o1.robots.RobotBody
import o1.robots.RobotBrain
import o1.util.assignments.{Argument, arg, InvalidSignature}
import o1.util.ConvenientSeq


/** The class `RobotType` represents instantiable kinds of robots whose availability
  * the Robots user interface checks dynamically.
  *
  * '''NOTE TO STUDENTS: In this course, you don't need to understand how this class works or can be used.''' */
sealed abstract class RobotType[+BrainClass <: RobotBrain](className: String, additionalParameterTypes: Class[_]*)
  extends DynamicClass[RobotBrain](className, Seq(classOf[String], classOf[RobotBody]) ++ additionalParameterTypes) with Product with Serializable {

  def apply(constructorArguments: Any*): Option[BrainClass] = Some(this.instantiate(constructorArguments.map(arg): _*))

  def instantiateRandom(name: String, body: RobotBody): BrainClass

  override def instantiate(parameters: Argument*) = super.instantiate(parameters: _*).asInstanceOf[BrainClass]

}


/** This companion object of class `RobotsType` provides access to a few standard instances of the class.
  *
  * '''NOTE TO STUDENTS: In this course, you don't need to understand how this object works or can be used.''' */
object RobotType {

  trait Basic[+BrainClass <: RobotBrain] {
    self: RobotType[BrainClass] =>
    def instantiateRandom(name: String, body: RobotBody): BrainClass = this.instantiate(arg(name), arg(body))
  }

  case object Spinbot    extends RobotType[o1.robots.Spinbot]("o1.robots.Spinbot")     with Basic[o1.robots.Spinbot]
  case object Nosebot    extends RobotType[o1.robots.Nosebot]("o1.robots.Nosebot")     with Basic[o1.robots.Nosebot]
  case object Psychobot  extends RobotType[o1.robots.Psychobot]("o1.robots.Psychobot") with Basic[o1.robots.Psychobot]

  case object Staggerbot extends RobotType[o1.robots.Staggerbot]("o1.robots.Staggerbot", classOf[Int]) {
    override def instantiateRandom(name: String, body: RobotBody) = {
      val randomInt = scala.util.Random.nextInt
      this.instantiate(arg(name), arg(body), arg(randomInt))
    }
  }

  case object Lovebot    extends RobotType[o1.robots.Lovebot]("o1.robots.Lovebot", classOf[RobotBody]) {
    override def instantiateRandom(name: String, body: RobotBody) = {
      val bots = body.world.robotList
      val randomExistingBot = bots.randomElement()
      val newLoveBot = this.instantiate(arg(name), arg(body), arg(randomExistingBot))
      assert(newLoveBot.beloved == randomExistingBot, "The lovebot's beloved variable was not initialized appropriately.")
      newLoveBot
    }
  }

  val All: Seq[RobotType[RobotBrain]] = Seq(Spinbot, Nosebot, Psychobot, Staggerbot, Lovebot)

}




// Workaround for a bug. To be refactored for 2022.
private[gui] class DynamicClass[Supertype <: AnyRef](private val className: String, private val constructorParameterTypes: Seq[Class[_]]) {
  import scala.util.{Try, Success, Failure}
  import java.lang.reflect.InvocationTargetException
  import java.lang.reflect.Constructor

  private val actualClass = Try(Class.forName(className).asInstanceOf[Class[Supertype]]) recoverWith {
    case noSuchClass: ClassNotFoundException => println("The class " + className + " was not available."); throw noSuchClass
    case invalidClass: ClassCastException    => invalidClass.printStackTrace(); throw invalidClass
  }

  val isUsable = {
    val constructor: Try[Constructor[_]] =
      Try(Class.forName(className).asInstanceOf[Class[Supertype]].getConstructor(constructorParameterTypes: _*))
    constructor.isSuccess
  }

  def instantiate(parameters: Argument*): Supertype =
    this.actualClass match {
      case Failure(_)           =>
        throw new InvalidSignature("The class " + this.className + " is not available and wasn't successfully instantiated.")
      case Success(actualClass) =>
        try {
          val constructor = actualClass.getConstructor(this.constructorParameterTypes: _*)
          constructor.newInstance((parameters.map( _.value )): _*)
        } catch {
          case problemWithinImplementation: InvocationTargetException =>
            println("The instantiation of class " + this.className + " failed to complete.")
            throw problemWithinImplementation
          case instantiationProblem: Exception                        =>
            throw new InvalidSignature("The class " + this.className + " wasn't successfully instantiated.")
        }
    }

}
