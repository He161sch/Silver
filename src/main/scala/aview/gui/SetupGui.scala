package aview.gui

import controller.{Controller, updateData}

import scala.swing._
import scala.swing.event.ButtonClicked
import controller.GameState._

class SetupGui(controller: Controller) extends Frame {
  listenTo(controller)
  peer.setLocationRelativeTo(null)
  title = "Silver"

  val undoButton = new Button {
    text = "\u2190"
  }

  val doButton = new Button {
    text = "\u2192"
  }

  val flowPanel = new FlowPanel {
    contents += undoButton
    contents += doButton
  }

  val inputName = new TextField {
    columns = 40
  }

  val playername = new Label {
    text = controller.getPlayerName
  }

  contents = new GridPanel(4, 1) {
    contents += playername
    contents += inputName
    contents += undoButton
    contents += doButton

    listenTo(undoButton)
    listenTo(doButton)

    reactions += {
      case ButtonClicked(component) => {
        if (component == undoButton) {
          controller.undoStep
        } else if (component == doButton) {
          controller.performSetPlayerName(inputName.text)
          if (controller.gameConfig.activePlayerIdx == 0) {
            dispose()
          }
        }

        if (controller.gameState == PLAYER_TURN) {
          //peer.setVisible(false)
          new BoardPanel(controller).visible = true
        }

        playername.text = controller.getPlayerName
        inputName.text = ""
      }
    }
  }

  reactions += {
    case event: updateData => {
      playername.text = controller.getPlayerName
      inputName.text = ""
    }
  }
}