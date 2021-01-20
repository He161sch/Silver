package de.htwg.se.aview.gui

import de.htwg.se.controller.controllercomponent.ControllerInterface

import scala.swing.{Button, Color, Dimension, Font, Frame, GridPanel}
import scala.swing.event.ButtonClicked

class savePanel(controller: ControllerInterface) extends Frame {
  background = new Color(0,100,0)
  visible = true
  centerOnScreen()

  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 20)

  peer.setPreferredSize(new Dimension(500,200))

  val saveCloseButton = new Button ("Save & Close ")
  val saveBackButton = new Button ("Save & Back")

  val buttons = new GridPanel(2,1) {
    contents += saveCloseButton
    contents += saveBackButton

  }
  listenTo(saveCloseButton, saveBackButton)

  reactions += {
    case ButtonClicked(component) => {
      if (component == saveBackButton) {
        controller.save
        dispose()
      } else if (component == saveCloseButton) {
        controller.save
        System.exit(1)
      }
    }
  }

  contents = new GridPanel(1,1) {
    contents += buttons
  }
}
