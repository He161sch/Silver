

import aview.TUI
import aview.gui.WelcomePanel
import com.google.inject.Guice
import controller.controllercomponent.ControllerInterface

import scala.util.{Failure, Success, Try}


object Silver {
  val injector = Guice.createInjector(new SilverModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new TUI(controller)
  val gui = new WelcomePanel(controller)


  def main(args: Array[String]): Unit = {
    tui.run()
  }

}