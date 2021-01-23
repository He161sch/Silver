package de.htwg.se.aview

import de.htwg.se.aview.gui.WelcomePanel
import de.htwg.se.controller.controllercomponent.ControllerInterface


trait UIFactory {
  def processCommands(input: String)
}

object UIFactory {
  def apply(kind: String, controller: ControllerInterface) = kind match {
    case "GUI" | "gui" => new WelcomePanel(controller)
    case "TUI" | "tui" => new TUI(controller).run()
    case "both" | "BOTH" => {
      new WelcomePanel(controller)
      new TUI(controller).run()
    }
  }
}
