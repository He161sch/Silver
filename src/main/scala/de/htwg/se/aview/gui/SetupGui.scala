package de.htwg.se.aview.gui

import com.malliina.audio.javasound.FileJavaSoundPlayer
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.se.controller.controllercomponent.GameState._
import de.htwg.se.controller.controllercomponent.{ControllerInterface, updateData}

import java.nio.file.Paths
import java.util.logging.LogManager

class SetupGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  peer.setPreferredSize(new Dimension(500,150))
  peer.setLocationRelativeTo(null)
  title = "Silver"

  val undoButton = new Button("\u2190" + " undo")
  val doButton = new Button("do " + "\u2192")




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

  this.defaultButton = doButton

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
        } else if(component == doButton) {
          controller.performSetPlayerName(inputName.text)
          if (controller.gameConfig.getActivePlayerIdx == 0) {
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
