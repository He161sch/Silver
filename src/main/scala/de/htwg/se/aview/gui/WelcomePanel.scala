package de.htwg.se.aview.gui

import de.htwg.se.controller.controllercomponent.ControllerInterface

import javax.swing._

import scala.swing.event.{ButtonClicked}
import scala.swing.{Label, _}


class WelcomePanel(controller: ControllerInterface) extends Frame {
  background = new Color(0,0,0)
  visible = true
  centerOnScreen()
  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)

  peer.setLocationRelativeTo(null)
  peer.setPreferredSize(new Dimension(525, 500))
  peer.setResizable(false)

  var logo = new BoxPanel(Orientation.Horizontal) {
    background = new Color(0, 0, 0)
    contents += new Label() {
      icon = new ImageIcon("src/main/images/Silver_logo.png")
      horizontalAlignment = Alignment.Center
    }
  }


  val twoPlayers = new Button("2 Players") {
    font = myFont
  }

  val threePlayers = new Button("3 Players") {
    font = myFont
  }

  val loadButton = new Button("Load Game") {
    font = myFont

  }

  val askPlayer = new Label("How many Players want to play?") {
    background = new Color(0, 0, 0)
    font = new Font("Herculanum", java.awt.Font.PLAIN, 35)
    foreground = new Color(255,255,255)
  }

  val flowPanel = new FlowPanel {

    background = new Color(0, 0, 0)
    contents += twoPlayers
    contents += threePlayers
    contents += loadButton


    listenTo(twoPlayers, threePlayers, loadButton)

    reactions += {
      case ButtonClicked(component) => {
        if (component == twoPlayers) {
          controller.performInitGame(2)
          new SetupGui(controller).visible = true
        } else if (component == threePlayers) {
          controller.performInitGame(3)
          new SetupGui(controller).visible = true
        } else if (component == loadButton) {
          controller.load
          new BoardPanel(controller).visible = true
        }
        peer.setVisible(false)
        dispose()
      }
    }
  }


  val grid = new GridPanel(2, 1) {
    background = new Color(0, 0, 0)
    contents += askPlayer
    contents += flowPanel

  }
  contents = new GridPanel(2, 1) {
    background = new Color(0, 0, 0)
    contents += logo
    contents += grid
  }


}
