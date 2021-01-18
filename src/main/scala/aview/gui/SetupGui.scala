package aview.gui

import controller.controllercomponent.controllerbaseimpl.Controller

import scala.swing._
import scala.swing.event.ButtonClicked
import controller.controllercomponent.GameState._
import controller.controllercomponent.updateData

class SetupGui(controller: Controller) extends Frame {
  listenTo(controller)
  peer.setPreferredSize(new Dimension(500,150))
  peer.setLocationRelativeTo(null)
  title = "Silver"

  // Do & undo Buttons
  val undoButton = new Button("\u2190" + " undo")
  val doButton = new Button("do " + "\u2192")

//  val flowPanel = new FlowPanel {
//    contents += undoButton
//    contents += doButton
//  }

  val inputName = new TextField {
    columns = 40
  }

  val playername = new Label {
    text = controller.getPlayerName
  }

  def createButtonGrid: GridPanel = new GridPanel(1,2) {
    contents += undoButton
    contents += doButton
  }

  undoButton.visible = false

  contents = new GridPanel(3,1) {
    contents += playername
    contents += inputName
    contents += createButtonGrid

    listenTo(undoButton)
    listenTo(doButton)

    reactions += {
      case ButtonClicked(component) => {
        if(component == undoButton) {
          controller.undoStep
          if(controller.gameConfig.activePlayerIdx == 0) {
            undoButton.visible = false
          }
        } else if(component == doButton) {
          undoButton.visible = true
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
