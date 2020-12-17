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
    text = "Undo"
  }
  val redoButton = new Button {
    text = "Redo"
  }
  val doButton = new Button {
    text = "Do"
  }

  val flowPanel = new FlowPanel {
    contents += undoButton
    contents += redoButton
    contents += doButton
  }

  val inputName = new TextField {
    columns = 40
  }

  val lbl_playername = new Label {
    text = controller.getPlayerName
  }

  contents = new GridPanel(3,1) {
    contents += lbl_playername
    contents += inputName
    contents += flowPanel
    listenTo(undoButton)
    listenTo(redoButton)
    listenTo(doButton)

    reactions += {
      case ButtonClicked(component) => {
        if(component == undoButton) {
          controller.undoStep
        } else if(component == redoButton) {
          controller.redoStep
        } else if(component == doButton) {
          controller.performSetPlayerName(inputName.text)
        }

        if (controller.gameState == PLAYER_TURN) {
          //peer.setVisible(false)
          new BoardPanel(controller).visible = true
        }

        lbl_playername.text = controller.getPlayerName
        inputName.text = ""
      }
    }
  }

  reactions += {
    case event: updateData => {
      lbl_playername.text = controller.getPlayerName
      inputName.text = ""
    }
  }
}
