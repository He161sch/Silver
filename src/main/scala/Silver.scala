
import scala.io.StdIn._
import aview.UIFactory
import controller.Controller


object Silver {

  val controller = new Controller()

  def main(args: Array[String]): Unit = {
    var input: String = ""

    val uiType = "TUI"
    val ui = UIFactory(uiType, controller)

    controller.notifyObservers
    do {
      input = readLine()
      ui.processCommands(input)
      //tui.processInputLine(input)
    } while (input != "q")
  }
}