package aview.gui

import controller.{Controller, updateData}

import scala.swing._
import scala.swing.event.ButtonClicked

class BoardPanel(controller: Controller) extends Frame {
  listenTo(controller)
  title = "Silver"
  peer.setPreferredSize(new Dimension(1000, 100))
  peer.setResizable(false)

  val pathToImage = "src/main/images/"
  val imageHeight = 100
  val imageWidth = 80


}
