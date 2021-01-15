package de.htwg.se.aview.gui

import java.awt.Image
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.controller.controllercomponent.{ControllerInterface, loadGame, updateData}

import javax.swing.ImageIcon
import scala.swing._
import scala.swing.event.ButtonClicked

class BoardPanel(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  title = "Silver"
  peer.setPreferredSize(new Dimension(1000, 1000))
  peer.setResizable(true)

  val myFont = new Font("Herculanum", java.awt.Font.PLAIN, 25)
  val pathToImage = "src/main/images/"
  val imageHeight = 160
  val imageWidth = 120


  val player = new Label {
    font = myFont
    text = controller.gameConfig.getActivePlayerName
  }

  val decktxt = new Label {
    font = myFont
    text = "Deck"
  }

  val discardPiletxt = new Label {
    font = myFont
    text = "Discard Pile"
  }

  val viewButton = new Button {
    text = "View"
    preferredSize = new Dimension(200, 100)
  }
  val drawButton = new Button {
    text = "Draw"
    preferredSize = new Dimension(200, 100)
  }
  val switchButton = new Button {
    text = "Switch"
    preferredSize = new Dimension(200, 100)
  }
  val combineButton = new Button {
    text = "Combine"
    preferredSize = new Dimension(200, 100)
  }
  val caboButton = new Button {
    text = "Cabo"
    preferredSize = new Dimension(200, 100)
  }
  val discardButton = new Button {
    text = "Discard"
    preferredSize = new Dimension(200, 100)
  }
  val discardPileButton = new Button {
    text = "Discard Pile"
    preferredSize = new Dimension(200, 100)
  }
  val nextPlayerButton = new Button {
    text = "Next Player"
    preferredSize = new Dimension(200, 100)
  }
  val saveGameButton = new Button {
    text = "Save Game"
    preferredSize = new Dimension(200, 100)
  }


  val helpButton = new Button {
    text = "Help Please"
    preferredSize = new Dimension(200, 100)
  }

  // VIEW BUTTONS

  val view1 = new Button ("1")
  val view2 = new Button ("2")
  val view3 = new Button ("3")
  val view4 = new Button ("4")
  val view5 = new Button ("5")

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

  def getDiscardCard(hidePlayerCards: Boolean): collection.mutable.Buffer[Component] = {
    var content = collection.mutable.Buffer[Component]()
    val cardList = controller.mapDiscardCard(hidePlayerCards)
    for(str <- cardList) {
      content += new Label {
        icon = scaledImageIcon(pathToImage + str, imageWidth, imageHeight)
      }
    }
    content
  }

  def createPlayerGrid(): GridPanel = new GridPanel(2,1) {
    contents += player
    contents += createPlayerCards()
  }

  def createCardGrid: GridPanel = new GridPanel(2,2) {
    contents += decktxt
    contents += discardPiletxt
    val drawedCard = getDrawedCard(false)
    for (content <- drawedCard) {
      contents += content
    }

    val discardCard = getDiscardCard(false)
    for (content <- discardCard) {
      contents += content
    }
  }

  def createPlayerCards(): FlowPanel = new FlowPanel {
    val cards = getCards(false)
    for (content <- cards) {
      contents += content
    }
  }

  val buttonFlowPanel = new FlowPanel {
    contents += drawButton
    contents += discardPileButton
    contents += viewButton
    contents += view1
    contents += view2
    contents += view3
    contents += view4
    contents += view5
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
    contents += discardButton
    contents += caboButton
    contents += saveGameButton
    contents += nextPlayerButton
    contents += helpButton

    discardPileButton.visible = false
    nextPlayerButton.visible = false
    discardButton.visible = false
    switchButton.visible = false
    combineButton.visible = false
    viewButtonsVisible(false)
    switchVisible(false)
    combineVisible(false)


    listenTo(drawButton, viewButton, switchButton, combineButton,
      caboButton, discardButton, view1, view2, view3, view4, view5,
      switch1, switch2, switch3, switch4, switch5, cb12, discardPileButton,
      cb13, cb14, cb15, cb23, cb24, cb25, cb34, cb35, cb45, nextPlayerButton,
      saveGameButton, helpButton)

    reactions += {
      case ButtonClicked(component) => {
        if (component == drawButton) {           // DRAW BUTTON
          controller.drawCard()
          viewSC(true)
          drawViewVisible(false)
          viewButtonsVisible(false)
          caboVisible(false)
          discardButton.visible = true
        } else if (component == combineButton) {        // COMBINE BUTTON
          viewSC(false)
          combineVisible(true)
          viewButtonsVisible(false)
          caboVisible(false)
        }else if (component == switchButton) {          // SWITCH BUTTON
          viewSC(false)
          switchVisible(true)
          viewButtonsVisible(false)
          caboVisible(false)
        } else if (component == switch1){               // SWITCH
          controller.switchCard(0)
          switchVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == switch2){
          controller.switchCard(1)
          switchVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == switch3){
          controller.switchCard(2)
          switchVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == switch4){
          controller.switchCard(3)
          switchVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == switch5){
          controller.switchCard(4)
          switchVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb12) {                  // COMBINE
          controller.combineCard(0, 1)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb13) {
          controller.combineCard(0, 2)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb14) {
          controller.combineCard(0, 3)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb15) {
          controller.combineCard(0, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb23) {
          controller.combineCard(1, 2)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb24) {
          controller.combineCard(1, 3)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb25) {
          controller.combineCard(1, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb34) {
          controller.combineCard(2, 3)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb35) {
          controller.combineCard(2, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == cb45) {
          controller.combineCard(3, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
        } else if (component == viewButton) {           // VIEW BUTTON
          controller.viewCard()
          viewSC(false)
          viewButtonsVisible(true)
          drawViewVisible(false)
          caboVisible(false)

        } else if (component == view1){
          controller.viewCard(0)
          drawViewVisible(false)
          caboVisible(false)
          viewButtonsVisible(false)
          nextPlayerButton.visible = true
        } else if (component == view2){
          controller.viewCard(1)
          drawViewVisible(false)
          caboVisible(false)
          viewButtonsVisible(false)
          nextPlayerButton.visible = true
        } else if (component == view3){
          controller.viewCard(2)
          drawViewVisible(false)
          caboVisible(false)
          viewButtonsVisible(false)
          nextPlayerButton.visible = true
        } else if (component == view4){
          controller.viewCard(3)
          drawViewVisible(false)
          caboVisible(false)
          viewButtonsVisible(false)
          nextPlayerButton.visible = true
        } else if (component == view5){
          controller.viewCard(4)
          drawViewVisible(false)
          caboVisible(false)
          viewButtonsVisible(false)
          nextPlayerButton.visible = true
        } else if (component == caboButton) {
          controller.whoWon()
          new GameOverPanel(controller)
          dispose()
        } else if (component == discardPileButton) {
          controller.drawFromDiscard()
          viewSC(true)
          drawViewVisible(false)
          caboVisible(false)
          discardButton.visible = false
         } else if (component == discardButton) {
          controller.discardCard()
          drawViewVisible(true)
          viewSC(false)
          caboVisible(true)
        } else if (component == nextPlayerButton) {
          controller.nextPlayer()
          drawViewVisible(true)
          caboVisible(true)
          nextPlayerButton.visible = false
        } else if (component == saveGameButton) {
          new savePanel(controller)
        } else if (component == helpButton) {
          new HelpPanel()
        }
      }
    }
  }

  contents = new GridPanel(3,1) {
    contents += createPlayerGrid()
    contents += createCardGrid
    contents += buttonFlowPanel
  }

  reactions += {
    case event: updateData => {
      redraw
      player.text = controller.gameConfig.getActivePlayerName
    }
    case event: loadGame => {
      redraw
      player.text = controller.gameConfig.getActivePlayerName
    }
  }

  def redraw: Unit = {
    val grid = new GridPanel(3,1) {
      contents += createPlayerGrid()
      contents += createCardGrid
      contents += buttonFlowPanel
    }
    contents = grid
  }

  def scaledImageIcon(path: String, width: Int, height: Int): ImageIcon = {
    val orig = new ImageIcon(path)
    val scaledImage = orig.getImage.getScaledInstance(width, height, Image.SCALE_DEFAULT)
    new ImageIcon(scaledImage)
  }

  def caboVisible(boolean: Boolean): Unit = {
    caboButton.visible = boolean
  }

  def switchVisible(boolean: Boolean): Unit = {
    switch1.visible = boolean
    switch2.visible = boolean
    switch3.visible = boolean
    switch4.visible = boolean
    switch5.visible = boolean
  }

  def viewButtonsVisible(boolean: Boolean): Unit = {
    view1.visible = boolean
    view2.visible = boolean
    view3.visible = boolean
    view4.visible = boolean
    view5.visible = boolean
  }

  def drawViewVisible(boolean: Boolean): Unit = {
    drawButton.visible = boolean
    viewButton.visible = boolean
    discardPileButton.visible = boolean
  }

  def viewSC(boolean: Boolean): Unit = {
    discardButton.visible = boolean
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


  // View:
  // Karten Panel ersetzen mit der Karte die man anschauen will
  // neuer Button: NextPlayer
  // sonst switcht es direkt um


}

