
import scala.io.StdIn._
import aview.TUI
import controller.Controller


object Silver {

  val controller = new Controller()
  val tui = new TUI(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    println("Welcome to Silver\n")
    var input: String = ""


    do{
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")

  }

}