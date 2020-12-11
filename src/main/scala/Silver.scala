
import scala.io.StdIn._
import aview.UIFactory
import controller.Controller

import scala.util.{Failure, Success, Try}


object Silver {

  val controller = new Controller()

  def main(args: Array[String]): Unit = {
    var input: String = ""

    val uiType = args(0)

    Try(UIFactory(uiType, controller)) match {
      case Failure(v) => println("Ui Failed because: " + v.getMessage)
      case Success(v) => println("See you next time :)")

    }
  }
}