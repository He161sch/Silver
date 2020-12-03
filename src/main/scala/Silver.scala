
import scala.io.StdIn._
import aview.TUI
import controller.{Controller, State}


object Silver {

  val controller = new Controller()
  val tui = new TUI(controller)
  controller.notifyObservers(State.CreatePlayer)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do{
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}