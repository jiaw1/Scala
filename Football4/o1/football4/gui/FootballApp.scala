
////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it's not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

package o1.football4.gui

import scala.swing._
import scala.swing.event._
import o1.gui.O1AppDefaults
import o1.football4._
import o1.gui.layout._
import o1.gui.Dialog._
import o1.util.assignments.{InvalidSignature, arg, withStudentSolution}
import scala.util._
import scala.swing.ListView.IntervalMode._


/** The singleton object `FootballApp` represents a simple application that a programmer
  * can use to experiment with the package `o1.football2`.
  *
  * '''NOTE TO STUDENTS: In this course, you don't need to understand how this object works
  * or can be used, apart from the fact that you can use this file to start the program.''' */
object FootballApp extends SimpleSwingApplication with O1AppDefaults {

  def top = new MainFrame {

    this.title = "Football4 Test App"
    val content = new SeasonPanel(this)
    this.contents = content
    this.defaultButton = content.startButton
    this.resizable = false
    this.pack()
    this.centerOnScreen()

    class SeasonPanel(val owner: Window) extends EasyPanel {

      val seasonClass = new DynamicClass[AnyRef]("o1.football4.Season", Seq())
      val season = Try(seasonClass.instantiate()).map(new EnhancedSeason(_)).recover {
        case missing: InvalidSignature => println(missing.message); throw missing
      }

      if (season.isFailure) {
        println("Season statistics and the list of matches will not be shown. Individual matches can still be tracked.")
      }

      val countStat, highestStat, latestStat = new Label
      val stats = new EasyPanel {
        placeNW(countStat,   (0, 0), OneSlot, Slight, (0, 5, 3, 5))
        placeNW(latestStat,  (1, 0), OneSlot, Slight, (0, 5, 3, 5))
        placeNW(highestStat, (1, 1), OneSlot, Slight, (0, 5, 3, 5))
      }
      val matchList = new ListView[String] {
        border = Swing.EtchedBorder
        selection.intervalMode = Single
      }
      this.listenTo(matchList.selection)
      reactions += {
        case ListSelectionChanged(source, _, isAdjusting) =>
          if (!isAdjusting) {
            for (season <- this.season; game <- season(source.selection.anchorIndex)) {
              this.matchView.game = game
            }
          }
      }

      val matchView = new MatchPanel(true)

      val startButton = new Button("New...")
      this.listenTo(startButton)
      reactions += {
        case ButtonClicked(source) =>
          this.addMatch()
      }

      val playedLabel = new Label("<html><b>Played:</b></html>")
      val matchScroller = new ScrollPane(matchList) {
        this.preferredSize = new Dimension(80, this.preferredSize.height)
      }

      withStudentSolution(SeasonPanel.this) {
        this.updateStatus()
      }

      placeNW(playedLabel,   (0, 0), OneSlot, Slight,          (5, 5, 3, 5))
      placeNW(matchScroller, (0, 1), OneSlot, FillVertical(1), (0, 5, 3, 5))
      placeNW(startButton,   (0, 2), OneSlot, Slight,          (0, 5, 3, 5))
      placeNW(matchView,     (1, 0), TwoHigh, FillBoth(1, 1),  (5, 5, 3, 5))
      placeNW(stats,         (1, 2), OneSlot, Slight,          (5, 5, 3, 0))

      def addMatch() = {
        for {
          home <- requestChoice("Home team:", ExampleLeague.Clubs,                        RelativeTo(this))
          away <- requestChoice("Away team:", ExampleLeague.Clubs.filterNot( _ == home ), RelativeTo(this))
        } {
          val dialog = new OngoingMatchDialog(this.owner, home, away)
          dialog.open()
          for (newMatch <- dialog.finishedMatch; season <- this.season) {
            withStudentSolution(SeasonPanel.this) {
              season.applyDynamic[Unit]("addResult")(classOf[Match] -> arg(newMatch)) // temporarily(?) rephrased because IJ complains unnecessarily about season.addResult[Unit](classOf[Match] -> arg(newMatch))
              this.updateStatus()
              this.matchList.selectIndices(season.numberOfMatches[Int] - 1)
            }
          }
        }
      }

      private def updateStatus() = {
        for (season <- this.season) {
          val matchText =  if (season.numberOfMatches[Int] == 1) "1 match" else (season.numberOfMatches[Int].toString + " matches")
          this.countStat.text   = s"<html><b>${matchText}.</b></html>"
          this.latestStat.text  = s"<html><b>Latest:</b> ${season.latestMatch[Option[Match]].getOrElse("n/a")}</html>"
          this.highestStat.text = s"<html><b>Biggest win:</b> ${season.biggestWin[Option[Match]].getOrElse("n/a")}</html>"
          this.matchList.listData = season.matches.map( _.toShortString )
        }
      }



      // dynamic wrapper around Season, with a couple of added convenience methods
      class EnhancedSeason(wrapped: AnyRef) extends DynamicObject(wrapped) {
        def apply(n: Int) = this.applyDynamic[Option[Match]]("matchNumber")(classOf[Int] -> arg(n)) // temporarily(?) rephrased because IJ complains unnecessarily about this.matchNumber[Option[Match]](classOf[Int] -> arg(n))
        def matches = (0 until this.numberOfMatches[Int]).flatMap( this(_) )
      }


      implicit class EnhancedMatch(val game: Match) {
        def toShortString = this.game.home.abbreviation + "-" + this.game.away.abbreviation
      }

    }

  }

}

// Workaround for a bug. To be refactored for 2022.
import o1.util.assignments.{Argument, arg, InvalidSignature}
import scala.util.{Try, Success, Failure}
import scala.reflect.ClassTag
import scala.language.dynamics
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Constructor
private[gui] class DynamicClass[Supertype <: AnyRef](private val className: String, private val constructorParameterTypes: Seq[Class[_]]) {

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
private[o1] class DynamicObject[StaticType](private val wrapped: StaticType) extends Dynamic {

  def applyDynamic[ResultType: ClassTag](methodName: String)(parameters: (Class[_], Argument)*): ResultType = {
    val returnValue = try {
      val method = this.wrapped.getClass.getMethod(methodName, parameters.map( _._1 ): _*)
      method.invoke(this.wrapped, parameters.map( _._2.value ): _*)
    } catch {
      case problemWithinImplementation: InvocationTargetException =>
        println("A call to the method " + methodName + " failed to complete.")
        throw problemWithinImplementation
      case otherProblem: Exception =>
        throw new InvalidSignature("The method or variable " + methodName + " was not successfully accessed.")
    }

    val boxings = Map[Class[_], Class[_]](classOf[Boolean] -> classOf[java.lang.Boolean], classOf[Int] -> classOf[java.lang.Integer], classOf[Double] -> classOf[java.lang.Double], classOf[Char] -> classOf[java.lang.Character], classOf[Short] -> classOf[java.lang.Short], classOf[Long] -> classOf[java.lang.Long], classOf[Byte] -> classOf[java.lang.Byte], classOf[Float] -> classOf[java.lang.Float])
    val expectedClassTag = implicitly[ClassTag[ResultType]]
    val expectedClass = expectedClassTag.runtimeClass
    if (expectedClassTag == ClassTag(classOf[Unit]) || expectedClass.isInstance(returnValue) || boxings.get(expectedClass).exists( _.isInstance(returnValue) ))
      returnValue.asInstanceOf[ResultType]
    else
      throw new InvalidSignature("The return value of " + methodName + " was not of the expected type.")
  }

  def selectDynamic[ResultType](methodName: String)(implicit expectedClassTag: ClassTag[ResultType]): ResultType = {
    this.applyDynamic[ResultType](methodName)()
  }

  def get[StaticType] = this.wrapped

}

