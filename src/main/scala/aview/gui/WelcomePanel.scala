package aview.gui

import java.awt.Image

import controller.Controller
import javax.swing._

import scala.swing.Swing.LineBorder
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.swing.{Label, _}
import scala.util.Try

class WelcomePanel(controller: Controller) extends Frame {
  background = new Color(0, 100, 0)
  visible = true
  centerOnScreen()
  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)

  peer.setLocationRelativeTo(null)
  peer.setPreferredSize(new Dimension(1000, 300))
  peer.setResizable(false)

  var logo = new BoxPanel(Orientation.Horizontal) {
    contents += new Label() {
      icon = new ImageIcon("src/main/images/Silver_logo.png")
    }
    contents += new Label() {
      text = "Welcome to Silver!"
    }
  }


    val twoPlayers: Button = new Button("2 Players") {
      font = myFont
    }

    val threePlayers: Button = new Button("3 Players") {
      font = myFont
    }

    val askPlayer = new Label("How many Players want to play?") {
      font = new Font("Herculanum", java.awt.Font.PLAIN, 40)
    }

    val flowPanel = new FlowPanel {


      contents += new FlowPanel()
      contents += twoPlayers
      contents += threePlayers


      listenTo(twoPlayers, threePlayers)

      reactions += {
        case ButtonClicked(component) => {
          if (component == `twoPlayers`) {
            controller.performInitGame(2)
            dispose()
          } else if (component == `threePlayers`) {
            controller.performInitGame(3)
            dispose()
          }
          new SetupGui(controller).visible = true
          peer.setVisible(false)
        }
      }
    }


    val grid = new GridPanel(2,1) {
      contents += askPlayer
      contents += flowPanel
    }
    contents = new GridPanel(2,1) {
      contents += logo
      contents += grid
    }

//  var menu = new BoxPanel(Orientation.Vertical) {
//
//    contents += new Label() {
//      text = "Welcome to Silver"
//    }
//
//    contents += new BoxPanel(Orientation.Horizontal) {
//      background = java.awt.Color.WHITE
//      contents += new Label() {
//        text = "How many Players want to play ?   "
//      }
//      val twoPlayers: Button = new Button("2 Players") {
//        font = myFont
//      }
//
//      val threePlayers: Button = new Button("3 Players") {
//        font = myFont
//      }
//      contents += twoPlayers
//      contents += threePlayers
//    }
//
//
//    val s = new Dimension(100, 50)
//    minimumSize = s
//    maximumSize = s
//    preferredSize = s
//    border = LineBorder(java.awt.Color.RED, 2)
//    background = java.awt.Color.WHITE
//  }
//
//
//
//
//  contents = new BorderPanel {
//    add(logo,BorderPanel.Position.North)
//    add(menu,BorderPanel.Position.Center)
//    background = java.awt.Color.BLACK
//  }
//
//  background = java.awt.Color.BLACK
//  size = new Dimension(700,200)
//  //resizable = false
//  open()
//
//  def invisible = visible = false
//
//
//
//
//
//

}
