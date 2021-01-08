package aview.gui

import java.awt.Image

import controller.controllercomponent.controllerbaseimpl.Controller
import controller.controllercomponent.updateData
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event.ButtonClicked

class BoardPanel(controller: Controller) extends Frame {
  listenTo(controller)
  title = "Silver"
  peer.setPreferredSize(new Dimension(1000, 1000))
  peer.setResizable(true)

  val pathToImage = "src/main/images/"
  val imageHeight = 160
  val imageWidth = 120


  val player = new Label {
    text = controller.getActivePlayerName
  }


  val viewButton = new Button ("View")
  val drawButton = new Button ("Draw")
  val switchButton = new Button ("Switch")
  val combineButton = new Button ("Combine")
  val caboButton = new Button ("Cabo")

  // SWITCH BUTTONS
  val switch1 = new Button ("1")
  val switch2 = new Button ("2")
  val switch3 = new Button ("3")
  val switch4 = new Button ("4")
  val switch5 = new Button ("5")

  // COMBINE BUTTONS
  val cb12 = new Button ("1 + 2")
  val cb13 = new Button ("1 + 3")
  val cb14 = new Button ("1 + 4")
  val cb15 = new Button ("1 + 5")
  val cb23 = new Button ("2 + 3")
  val cb24 = new Button ("2 + 4")
  val cb25 = new Button ("2 + 5")
  val cb34 = new Button ("3 + 4")
  val cb35 = new Button ("3 + 5")
  val cb45 = new Button ("4 + 5")

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

  def getDrawedCard(hidePlayerCards: Boolean): collection.mutable.Buffer[Component] = {
    var content = collection.mutable.Buffer[Component]()
    val cardList = controller.mapDrawedCard(hidePlayerCards)
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

  def createDrawedCard: FlowPanel = new FlowPanel {
    val cards = getDrawedCard(false)
    for (content <- cards) {
      contents += content
    }
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
    contents += switch1
    contents += switch2
    contents += switch3
    contents += switch4
    contents += switch5
    contents += switchButton
    contents += combineButton
    contents += cb12
    contents += cb13
    contents += cb14
    contents += cb15
    contents += cb23
    contents += cb24
    contents += cb25
    contents += cb34
    contents += cb35
    contents += cb45
    contents += caboButton


    switchButton.visible = false
    combineButton.visible = false
    switchVisible(false)
    combineVisible(false)


    listenTo(drawButton, viewButton, switchButton, combineButton, caboButton, switch1, switch2, switch3, switch4, switch5, cb12, cb13, cb14, cb15, cb23, cb24, cb25, cb34, cb35, cb45)

    reactions += {
      case ButtonClicked(component) => {
        if (component == drawButton) {
          controller.drawCard()
          viewSC(true)
          drawViewVisible(false)
        } else if (component == combineButton) {
          viewSC(false)
          combineVisible(true)
        }else if (component == switchButton) {          // SWITCH BUTTON
          viewSC(false)
          switchVisible(true)
        } else if (component == switch1){               // SWITCH
          controller.switchCard(0)
          switchVisible(false)
          drawViewVisible(true)
        } else if (component == switch2){
          controller.switchCard(1)
          switchVisible(false)
          drawViewVisible(true)
        }else if (component == switch3){
          controller.switchCard(2)
          switchVisible(false)
          drawViewVisible(true)
        }else if (component == switch4){
          controller.switchCard(3)
          switchVisible(false)
          drawViewVisible(true)
        }else if (component == switch5){
          controller.switchCard(4)
          switchVisible(false)
          drawViewVisible(true)
        }else if (component == cb12) {                  // COMBINE
          controller.combineCard(0, 1)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb13) {
          controller.combineCard(0, 2)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb14) {
          controller.combineCard(0, 3)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb15) {
          controller.combineCard(0, 4)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb23) {
          controller.combineCard(1, 2)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb24) {
          controller.combineCard(1, 3)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb25) {
          controller.combineCard(1, 4)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb34) {
          controller.combineCard(2, 3)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb35) {
          controller.combineCard(2, 4)
          combineVisible(false)
          drawViewVisible(true)
        }else if (component == cb45) {
          controller.combineCard(3, 4)
          combineVisible(false)
          drawViewVisible(true)
        } else if (component == caboButton) {
          controller.whoWon()
          new GameOverPanel(controller)
          dispose()
        }
      }
    }
  }

  contents = new GridPanel(3,1) {
    contents += createPlayerGrid
    contents += createDrawedCard
    contents += buttonFlowPanel
  }

  reactions += {
    case event: updateData => {redraw}

      player.text = controller.getActivePlayerName
  }

  def redraw: Unit = {
    val grid = new GridPanel(3,1) {
      contents += createPlayerGrid
      contents += createDrawedCard
      contents += buttonFlowPanel
    }
    contents = grid
  }

  def scaledImageIcon(path: String, width: Int, height: Int): ImageIcon = {
    val orig = new ImageIcon(path)
    val scaledImage = orig.getImage.getScaledInstance(width, height, Image.SCALE_DEFAULT)
    new ImageIcon(scaledImage)
  }

  def switchVisible(boolean: Boolean): Unit = {
    switch1.visible = boolean
    switch2.visible = boolean
    switch3.visible = boolean
    switch4.visible = boolean
    switch5.visible = boolean
  }

  def drawViewVisible(boolean: Boolean): Unit = {
    drawButton.visible = boolean
    viewButton.visible = boolean
  }

  def viewSC(boolean: Boolean): Unit = {
    switchButton.visible = boolean
    combineButton.visible = boolean
  }


  def combineVisible(boolean: Boolean): Unit ={
    cb12.visible = boolean
    cb13.visible = boolean
    cb14.visible = boolean
    cb15.visible = boolean
    cb23.visible = boolean
    cb24.visible = boolean
    cb25.visible = boolean
    cb34.visible = boolean
    cb35.visible = boolean
    cb45.visible = boolean
  }


  // BoxPanel unten drunter
  // Image einführen


}

