
import scala.io.StdIn._
import aview.TUI
import controller.Controller


object Silver extends Runnable {

  val controller = new Controller()
  val tui = new TUI(controller)
  controller.notifyObservers

  def run(): Unit = {
    var input: String = " "
    do{
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }

  def main(args: Array[String]): Unit = {
//    var input: String = " "
//    do{
//      input = readLine()
//      tui.processInputLine(input)
//    } while (input != "q")
    Silver.run()
  }
}