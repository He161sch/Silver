
import scala.io.StdIn._
import aview.TUI
import aview.gui.GUI
import controller.Controller

import scala.util.{Failure, Success, Try}


object Silver {

  val controller = new Controller()
  val tui = new TUI(controller)
  val gui = new GUI(controller)

  def main(args: Array[String]): Unit = {


    tui.run()
  }

}