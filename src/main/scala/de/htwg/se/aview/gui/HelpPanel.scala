package de.htwg.se.aview.gui


import scala.swing.{BoxPanel, Button, Color, Dimension, FlowPanel, Font, Frame, GridPanel, Label, Orientation}
import scala.swing.event.ButtonClicked

class HelpPanel() extends Frame {
  background = new Color(0,100,0)
  visible = true
  centerOnScreen()

  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)

  peer.setPreferredSize(new Dimension(600,350))

  var help = new BoxPanel(Orientation.Horizontal) {
    contents += new Label() {
      font = myFont
      text += "<html><p style=\"text-align:left;\">Du musst versuchen dir deine Karten zu merken und am Ende so wenig Punkte wie möglich zu haben</p>" +
        "<p style=\"text-align:left;\">Falls du in der Gui die Buttons suchst. Die 5 Karten sind Switch Buttons und die unteren sind Draw Buttons! :)</p>" +
        "<p style=\"text-align:left;\">Bei Draw wird eine Karte gezogen</p>" +
        "<p style=\"text-align:left;\">Bei DiscardPile wird von Ablagestapel gezogen</p>" +
        "<p style=\"text-align:left;\">Danach kann getauscht(Switch) oder verschmolzen(Combine) werde</p>" +
        "<p style=\"text-align:left;\">Bei View kann sich eine Karte angeschaut werden</p>" +
        "<p style=\"text-align:left;\">Cabo beendet das Spiel und der gewinner wird berechnet</p></html>"
    }
  }

  val closeButton = new Button {
    text = "Close"
    preferredSize = new Dimension(200, 100)
  }

  val flowPanel = new FlowPanel {
    contents += closeButton

    listenTo(closeButton)


    reactions += {
      case ButtonClicked(component) => {
        if (component == closeButton) {
          dispose()
        }
      }
    }
  }

  contents = new GridPanel(2,1) {
    contents += help
    contents += flowPanel
  }

}
