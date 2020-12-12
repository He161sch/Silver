package aview

import controller.Controller

trait UIInterface { // Factory Method

  def inputCommand(input: String)
}

object UIInterface {
  def apply(kind: String, controller: Controller) = kind match {
    case "GUI" => new GUI(controller)
    case "TUI" => new TUI(controller)
  }
}