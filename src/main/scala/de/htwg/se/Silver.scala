package de.htwg.se

import com.google.inject.Guice
import de.htwg.se.aview.TUI
import de.htwg.se.aview.gui.WelcomePanel
import de.htwg.se.controller.controllercomponent.ControllerInterface

object Silver {
  val injector = Guice.createInjector(new SilverModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new TUI(controller)

  // An die Peer Review Dudes und Dudinen ihr müsst in IntelliJ rechts oben neben Silver auf den Pfeil klicken
  // dann auf Edit Configurations und dann ne Environment Variable hinzufügen: UI_Type = full
  val UIType: Boolean = if (System.getenv("UI_TYPE").equals("full")) true else false

  def main(args: Array[String]): Unit = {
    if (UIType) {
      val gui = new WelcomePanel(controller)
      gui.visible = true
      tui.run()
    } else {
      tui.run()
    }
  }
}
