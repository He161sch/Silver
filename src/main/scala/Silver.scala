
import scala.io.StdIn._
import aview.UIFactory
import controller.Controller
import model.Deck


object Silver {

  val deck = new Deck()
  val controller = new Controller(deck)

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