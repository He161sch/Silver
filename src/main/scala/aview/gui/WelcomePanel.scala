package aview.gui

import controller.Controller
import javax.swing.BorderFactory

import scala.swing._
import scala.swing.event.ButtonClicked

class WelcomePanel(controller: Controller) extends BoxPanel(Orientation.Vertical) {
  background = new Color(0, 100, 0)
  border = BorderFactory.createEmptyBorder(10,10,10,10)
  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)


  val twoPlayers: Button = new Button("2 Players") {
    font = myFont
  }

  val threePlayers: Button = new Button("3 Players") {
    font = myFont
  }

  contents += new FlowPanel() {
    contents += new Label("How many Players want to play?") {
      font = new Font("Herculanum", java.awt.Font.PLAIN, 40)
    }
  }

  contents += new FlowPanel() {
    contents += twoPlayers
    contents += threePlayers
  }

  listenTo(twoPlayers, threePlayers)

  reactions += {
    case ButtonClicked(`twoPlayers`) => {
      controller.performInitGame(2)
      new NamePanel(controller).visible = true
    }
    case ButtonClicked(`threePlayers`) => {
      controller.performInitGame(3)
      new NamePanel(controller).visible = true
    }
  }

}
