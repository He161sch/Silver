package aview.gui


import controller.{Controller, updateData}
import controller.GameState._

import scala.swing._

class GUI(controller: Controller) extends Frame {
  listenTo(controller)

  title = "Silver"

  contents = new WelcomePanel(controller)

  visible = true
  centerOnScreen()
  resizable = true
  pack()

  reactions += {
    case event: updateData => update
  }

  def update: Unit = {
    controller.gameState match {
      case WelcomeState => new WelcomePanel(controller)
      case InputName => new NamePanel(controller)
    }
  }
}


