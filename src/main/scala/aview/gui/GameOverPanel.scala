package aview.gui

import controller.controllercomponent.ControllerInterface
import controller.controllercomponent.controllerbaseimpl.Controller

import javax.swing.BorderFactory
import scala.swing._

class GameOverPanel(controller: ControllerInterface) extends Frame {
  background = new Color(0,100,0)
  visible = true
  centerOnScreen()

  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 50)

  peer.setPreferredSize(new Dimension(500,200))

  var winner = new BoxPanel(Orientation.Horizontal) {
    contents += new Label() {
      text = controller.gameConfig.winnerToString()
    }
  }


  contents = new GridPanel(1,1) {
    contents += winner
    font = myFont
  }
}
