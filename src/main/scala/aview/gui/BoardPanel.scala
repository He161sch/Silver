package aview.gui

import java.awt.Image

import controller.{Controller, updateData}
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event.ButtonClicked

class BoardPanel(controller: Controller) extends Frame {
  listenTo(controller)
  title = "Silver"
  peer.setPreferredSize(new Dimension(1000, 1000))
  peer.setResizable(true)

  val pathToImage = "src/main/images/"
  val imageHeight = 100
  val imageWidth = 80


  val player = new Label {
    text = controller.getActivePlayerName
  }

  val nextPlayer = new Button("Next Players Turn")

  val viewButton = new Button ("View")
  val drawButton = new Button ("Draw")
  val switchButton = new Button ("Switch")
  val combineButton = new Button ("Combine")


  val oneButton = new Button ("1")
  val twoButton = new Button ("2")
  val threeButton = new Button ("3")
  val fourButton = new Button ("4")
  val fiveButton = new Button ("5")

  def getCards(hidePlayerCards: Boolean): collection.mutable.Buffer[Component] = {
    var content = collection.mutable.Buffer[Component]()
    val cardList = controller.mapSymbolToChar(hidePlayerCards)
    for(str <- cardList) {
      content += new Label {
        icon = scaledImageIcon(pathToImage + str, imageWidth, imageHeight)
      }
    }
    content
  }

  def createPlayerGrid: GridPanel = new GridPanel(2,1) {
    contents += player
    contents += createPlayerCards
  }

  def createPlayerCards: FlowPanel = new FlowPanel {
    val cards = getCards(false)
    for (content <- cards) {
      contents += content
    }
  }

  val buttonFlowPanel = new FlowPanel {
    contents += drawButton
    contents += viewButton
    contents += oneButton
    contents += twoButton
    contents += threeButton
    contents += fourButton
    contents += fiveButton
    contents += switchButton
    contents += combineButton
    contents += nextPlayer

    nextPlayer.visible = false

    switchButton.visible = false
    combineButton.visible = false
    allNumberButtonsVisible(false)


    listenTo(drawButton, viewButton, switchButton,
      combineButton, oneButton, twoButton, threeButton, fourButton, fiveButton, nextPlayer)

    reactions += {
      case ButtonClicked(component) => {
        if (component == drawButton) {
          controller.drawCard()
          drawViewVisible(false)
          switchButton.visible = true
        } else if (component == viewButton) {
          controller.viewCard()
          drawViewVisible(false)
          allNumberButtonsVisible(true)
        } else if (component == switchButton) {
          switchButton.visible = false
          combineButton.visible = false
          allNumberButtonsVisible(true)
          reactions += {
            case ButtonClicked(component) => {
              if (component == oneButton) {
                controller.switchCard(0)
                nextPlayer.visible = true
                drawViewVisible(true)
                allNumberButtonsVisible(false)
              } else if (component == twoButton) {
                controller.switchCard(1)
                drawViewVisible(true)
                allNumberButtonsVisible(false)
              } else if (component == threeButton) {
                controller.switchCard(2)
                allNumberButtonsVisible(false)
                drawViewVisible(true)
              } else if (component == fourButton) {
                controller.switchCard(3)
                nextPlayer.visible = true
                allNumberButtonsVisible(false)
                drawViewVisible(true)
              } else if (component == fiveButton) {
                controller.switchCard(4)
                allNumberButtonsVisible(false)
                drawViewVisible(true)
              }
            }
          }
        } else if (component == combineButton) {
          allNumberButtonsVisible(true)
        }
      }
    }
  }

  contents = new GridPanel(2,1) {
    contents += createPlayerGrid
    contents += buttonFlowPanel
  }

  reactions += {
    case event: updateData => {redraw}

      player.text = controller.getActivePlayerName
  }

  def redraw: Unit = {
    val grid = new GridPanel(2,1) {
      contents += createPlayerGrid
      contents += buttonFlowPanel
    }
    contents = grid
  }

  def scaledImageIcon(path: String, width: Int, height: Int): ImageIcon = {
    val orig = new ImageIcon(path)
    val scaledImage = orig.getImage.getScaledInstance(width, height, Image.SCALE_DEFAULT)
    new ImageIcon(scaledImage)
  }

  def allNumberButtonsVisible(boolean: Boolean): Unit = {
    oneButton.visible = boolean
    twoButton.visible = boolean
    threeButton.visible = boolean
    fourButton.visible = boolean
    fiveButton.visible = boolean
  }

  def drawViewVisible(boolean: Boolean): Unit = {
    drawButton.visible = boolean
    viewButton.visible = boolean
  }


}
