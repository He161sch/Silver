package de.htwg.se.aview.gui

import de.htwg.se.controller.controllercomponent.ControllerInterface

import java.awt.Image
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller

import javax.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.swing.{Label, _}
import scala.util.Try

class WelcomePanel(controller: ControllerInterface) extends Frame {
  background = new Color(0,100,0)
  visible = true
  centerOnScreen()
  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)

  peer.setLocationRelativeTo(null)
  peer.setPreferredSize(new Dimension(525, 500))
  peer.setResizable(false)

  var logo = new BoxPanel(Orientation.Horizontal) {
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
    font = new Font("Herculanum", java.awt.Font.PLAIN, 35)
  }

  val flowPanel = new FlowPanel {


    contents += new FlowPanel()
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
    contents += askPlayer
    contents += flowPanel

  }
  contents = new GridPanel(2, 1) {
    contents += logo
    contents += grid
  }


}
