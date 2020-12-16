package aview.gui;

import controller.Controller
import javax.swing.BorderFactory

import scala.swing.BoxPanel
import scala.swing.MenuBar.NoMenuBar.border
import scala.swing.event._
import swing._

class NamePanel(controller:Controller) extends Frame {
  contents = new BoxPanel(Orientation.Vertical) {
    background = new Color(0, 100, 0)
    border = BorderFactory.createEmptyBorder(10,10,10,10)
    centerOnScreen()
    val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)


    val nameInput: TextField = new TextField() {
      listenTo(keys)
      reactions += {
        case KeyPressed(_, Key.Enter, _, _) => None
      }
    }

    val nextPlayer = new Button("Enter")
    val lastPlayer = new Button("Undo")

    contents += new FlowPanel() {
      contents += new Label("Player " + controller.gameConfig.activePlayerIdx + " please enter your name :)" ) {
        font = myFont
      }
    }

    contents += nameInput

    contents += new BoxPanel(Orientation.Vertical) {
      contents += lastPlayer
      contents += nextPlayer
    }
    listenTo(nextPlayer, lastPlayer)
    reactions += {
      case ButtonClicked(`nextPlayer`) => controller.performSetPlayerName(nameInput.text)
      case ButtonClicked(`lastPlayer`) => controller.undoStep
    }

  }
}
