package de.htwg.se.aview.gui

import com.malliina.audio.javasound.FileJavaSoundPlayer

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.se.controller.controllercomponent.GameState._
import de.htwg.se.controller.controllercomponent.{ControllerInterface, StartGame, updateData}

import java.nio.file.{Path, Paths}
import java.util.logging.LogManager

class SetupGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  peer.setPreferredSize(new Dimension(500,150))
  peer.setLocationRelativeTo(null)
  title = "Silver"

  val undoButton = new Button("\u2190" + " undo")
  val doButton = new Button("do " + "\u2192")

  val file: Path = Paths get "src/main/images/deckShuffle.mp3"
  LogManager.getLogManager.reset()
  val deckShuffle = new FileJavaSoundPlayer(file)

  var boardPanel = new BoardPanel(controller)
  val inputName: TextField = new TextField {
    columns = 40
  }

  val playername: Label = new Label {
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

    undoButton.enabled = false

    reactions += {
      case ButtonClicked(component) => {
        if(component == undoButton) {
          controller.undoStep
          if(controller.gameConfig.getActivePlayerIdx == 0) {
            undoButton.enabled = false
          }
        } else if(component == doButton) {
          undoButton.enabled = true
          controller.performSetPlayerName(inputName.text)
          if (controller.gameConfig.getActivePlayerIdx == 0) {
            dispose()
            deckShuffle.play()
            deckShuffle.volume = 20
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
  def redraw: Unit = {
    playername.text = controller.getPlayerName
    inputName.text = ""
  }

  reactions += {
    case event: updateData => {
      redraw
      playername.text = controller.getPlayerName
      inputName.text = ""
    }
    case event: StartGame => {
      boardPanel.deafTo(controller)
      boardPanel = new BoardPanel(controller)
      boardPanel.pack()
      boardPanel.visible = true
      dispose()
    }
  }
}
