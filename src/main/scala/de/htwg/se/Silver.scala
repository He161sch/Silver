package de.htwg.se

import com.google.inject.Guice
import de.htwg.se.aview.TUI
import de.htwg.se.aview.gui.WelcomePanel
import de.htwg.se.controller.controllercomponent.ControllerInterface

object Silver {
  val injector = Guice.createInjector(new SilverModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new TUI(controller)
  val gui = new WelcomePanel(controller)

  def main(args: Array[String]): Unit = {
    tui.run()
  }
}
