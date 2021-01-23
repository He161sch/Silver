package de.htwg.se.aview.gui

import de.htwg.se.controller.controllercomponent.ControllerInterface
import scala.swing._
import scala.swing.event.ButtonClicked

class GameOverPanel(controller: ControllerInterface) extends Frame {
  background = new Color(0,100,0)
  visible = true
  centerOnScreen()

  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)

  peer.setPreferredSize(new Dimension(500,200))

  var winner = new BoxPanel(Orientation.Horizontal) {
    contents += new Label() {
      font = myFont
      text += "<html><p style=\"text-align;left;\">" + controller.gameConfig.winnerToString() + "</p></html>"
    }
  }

  val newGameButton = new Button ("New Game")
  val exitButton = new Button ("Exit")

  val buttons = new GridPanel(2,1) {
    contents += newGameButton
    contents += exitButton

    listenTo(newGameButton, exitButton)

    reactions += {
      case ButtonClicked(component) => {
        if (component == newGameButton) {
          controller.newGame
          new WelcomePanel(controller)
          dispose()
        } else if (component == exitButton) {
          System.exit(1)
        }
      }
    }
  }

  contents = new GridPanel(2,1) {
    contents += winner
    contents += buttons
  }

}
