

import aview.TUI
import aview.gui.WelcomePanel
import _root_.controller.controllercomponent.controllerbaseimpl.Controller

import scala.util.{Failure, Success, Try}


object Silver {

  val controller = new Controller()
  val tui = new TUI(controller)
  val gui = new WelcomePanel(controller)


  def main(args: Array[String]): Unit = {
    tui.run()
  }

}