package de.htwg.se.aview.gui

import de.htwg.se.controller.controllercomponent.GameState.{DRAWEDCARD, VIEWCARD}

import java.awt.Image

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
    preferredSize = new Dimension(120, 180)
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
    preferredSize = new Dimension(120, 180)
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
    text = "Help"
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
  val cb12 = new Button {
    text = "1 + 2"
    preferredSize = new Dimension(100, 50)
  }
  val cb13 = new Button {
    text = "1 + 3"
    preferredSize = new Dimension(100, 50)
  }
  val cb14 = new Button {
    text = "1 + 4"
    preferredSize = new Dimension(100, 50)
  }
  val cb15 = new Button {
    text = "1 + 5"
    preferredSize = new Dimension(100, 50)
  }
  val cb23 = new Button {
    text = "2 + 3"
    preferredSize = new Dimension(100, 50)
  }
  val cb24 = new Button {
    text = "2 + 4"
    preferredSize = new Dimension(100, 50)
  }
  val cb25 = new Button {
    text = "2 + 5"
    preferredSize = new Dimension(100, 50)
  }
  val cb34 = new Button {
    text = "3 + 4"
    preferredSize = new Dimension(100, 50)
  }
  val cb35 = new Button {
    text = "3 + 5"
    preferredSize = new Dimension(100, 50)
  }
  val cb45 = new Button {
    text = "4 + 5"
    preferredSize = new Dimension(100, 50)
  }

  def getCards(hidePlayerCards: Boolean): collection.mutable.Buffer[Component] = {
    var content = collection.mutable.Buffer[Component]()
    val cardList = controller.mapSymbolToChar(hidePlayerCards)
    var i = 0
    for(str <- cardList) {
      content += new Button {
        icon = scaledImageIcon(pathToImage + str, imageWidth, imageHeight)
        opaque = false
        focusPainted = false
        contentAreaFilled = false
        borderPainted = false

        i match {

          case 0 => reactions += {
            case ButtonClicked(_) => {

              // SWITCH
              if (controller.gameState == DRAWEDCARD) {
                controller.switchCard(0)
                switchVisible(false)
                drawViewVisible(true)
                caboVisible(true)
                viewSC(false)
              } else if (controller.gameState == VIEWCARD) {
                controller.viewCard(0)
                drawViewVisible(false)
                caboVisible(false)
                viewButtonsVisible(false)
                nextPlayerButton.visible = true
              }
            }
          }
          case 1 => reactions += {
            case ButtonClicked(_) => {
              if (controller.gameState == DRAWEDCARD) {
                controller.switchCard(1)
                switchVisible(false)
                drawViewVisible(true)
                caboVisible(true)
                viewSC(false)
              } else if (controller.gameState == VIEWCARD) {
                controller.viewCard(1)
                drawViewVisible(false)
                caboVisible(false)
                viewButtonsVisible(false)
                nextPlayerButton.visible = true
              }
            }
          }
          case 2 => reactions += {
            case ButtonClicked(_) => {
              if (controller.gameState == DRAWEDCARD) {
                controller.switchCard(2)
                switchVisible(false)
                drawViewVisible(true)
                caboVisible(true)
                viewSC(false)
              } else if (controller.gameState == VIEWCARD) {
                controller.viewCard(2)
                drawViewVisible(false)
                caboVisible(false)
                viewButtonsVisible(false)
                nextPlayerButton.visible = true
              }

            }
          }
          case 3 => reactions += {
            case ButtonClicked(_) => {
              if (controller.gameState == DRAWEDCARD) {
                controller.switchCard(3)
                switchVisible(false)
                drawViewVisible(true)
                caboVisible(true)
                viewSC(false)
              } else if (controller.gameState == VIEWCARD) {
                controller.viewCard(3)
                drawViewVisible(false)
                caboVisible(false)
                viewButtonsVisible(false)
                nextPlayerButton.visible = true
              }
            }
          }

          case 4 => reactions += {
            case ButtonClicked(_) => {
              if (controller.gameState == DRAWEDCARD) {
                controller.switchCard(4)
                switchVisible(false)
                drawViewVisible(true)
                caboVisible(true)
                viewSC(false)
              } else if (controller.gameState == VIEWCARD){
                controller.viewCard(4)
                drawViewVisible(false)
                caboVisible(false)
                viewButtonsVisible(false)
                nextPlayerButton.visible = true
              }
            }

          }
        }
      }
      i = i + 1
    }
    content
  }

  def getDrawedCard(hidePlayerCards: Boolean): collection.mutable.Buffer[Component] = {
    var content = collection.mutable.Buffer[Component]()
    val cardList = controller.mapDrawedCard(hidePlayerCards)
    for(str <- cardList) {
      content += new Button {
        opaque = false
        focusPainted = false
        contentAreaFilled = false
        borderPainted = false
        icon = scaledImageIcon(pathToImage + str, imageWidth, imageHeight)
        reactions += {
          case ButtonClicked(_) => { // DRAW BUTTON
            controller.drawCard()
            viewSC(true)
            drawViewVisible(false)
            viewButtonsVisible(false)
            caboVisible(false)
            discardButton.visible = true
          }
        }
      }
    }
    content
  }

  def getDiscardCard(hidePlayerCards: Boolean): collection.mutable.Buffer[Component] = {
    var content = collection.mutable.Buffer[Component]()
    val cardList = controller.mapDiscardCard(hidePlayerCards)
    for(str <- cardList) {
      content += new Button {
        icon = scaledImageIcon(pathToImage + str, imageWidth, imageHeight)
        opaque = false
        focusPainted = false
        contentAreaFilled = false
        borderPainted = false
        reactions += {
          case ButtonClicked(_) => {
            controller.drawFromDiscard()
            viewSC(true)
            drawViewVisible(false)
            caboVisible(false)
            discardButton.visible = false
          }
        }
      }
    }
    content
  }

  def createPlayerGrid(): GridPanel = new GridPanel(2,1) {
    opaque = false
    contents += player
    contents += createPlayerCards()
  }



  def createCardGrid: FlowPanel = new FlowPanel() {
    opaque = false

    val deckTxt = new GridPanel(1, 1) {
      opaque = false
      contents += decktxt
//      preferredSize = new Dimension (50, 50)
    }
    val deckPanel = new GridPanel(1, 1) {
      opaque = false
      val drawedCard = getDrawedCard(false)
      for (content <- drawedCard) {
        contents += content
      }
//      preferredSize = new Dimension(250,350)
    }

    val fullDeckPanel = new GridPanel(2, 1) {
      opaque = false
      contents += deckTxt
      contents += deckPanel
//      preferredSize = new Dimension (500, 500)
    }

    val discardPileTxt = new GridPanel(1,1) {
      opaque = false
      contents += discardPiletxt
//      preferredSize = new Dimension (50, 50)
    }

    val discardPanel = new GridPanel(1,1) {
      opaque = false
      val discardCard = getDiscardCard(false)
      for (content <- discardCard) {
        contents += content
      }
//      preferredSize = new Dimension(250,350)
    }

    val fullDiscardPanel = new GridPanel(2, 1) {
      opaque = false
      contents += discardPileTxt
      contents += discardPanel

    }
    contents += new GridPanel(1, 2) {
      opaque = false
      contents += fullDeckPanel
      contents += fullDiscardPanel
    }

  }

  def createPlayerCards(): FlowPanel = new FlowPanel {
    opaque = false
    val cards = getCards(false)
    for (content <- cards) {
      contents += content
    }
  }

  val buttonFlowPanel = new FlowPanel {
    opaque = false
    contents += viewButton
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


    listenTo(viewButton, combineButton, caboButton,
      discardButton, view1, view2, view3, view4, view5,
      switch1, switch2, switch3, switch4, switch5, cb12,
      cb13, cb14, cb15, cb23, cb24, cb25, cb34, cb35, cb45,
      nextPlayerButton, saveGameButton, helpButton)

    reactions += {
      case ButtonClicked(component) => {
        if (component == combineButton) {        // COMBINE BUTTON
          viewSC(false)
          combineVisible(true)
          viewButtonsVisible(false)
          caboVisible(false)
          saveHelpSmall()
        } else if (component == cb12) {                  // COMBINE
          controller.combineCard(0, 1)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb13) {
          controller.combineCard(0, 2)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb14) {
          controller.combineCard(0, 3)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb15) {
          controller.combineCard(0, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb23) {
          controller.combineCard(1, 2)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb24) {
          controller.combineCard(1, 3)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb25) {
          controller.combineCard(1, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb34) {
          controller.combineCard(2, 3)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb35) {
          controller.combineCard(2, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == cb45) {
          controller.combineCard(3, 4)
          combineVisible(false)
          drawViewVisible(true)
          caboVisible(true)
          saveHelpBig()
        } else if (component == viewButton) {           // VIEW BUTTON
          controller.viewCard()
          viewSC(false)
          viewButtonsVisible(true)
          drawViewVisible(false)
          caboVisible(false)
        } else if (component == caboButton) {
          controller.whoWon()
          new GameOverPanel(controller)
          dispose()
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

  contents = new ImagePanel() {
    imagePath = "src/main/images/Background.png"

    contents += new GridPanel(3, 1) {
      opaque = false
      contents += createPlayerGrid()
      contents += createCardGrid
      contents += buttonFlowPanel
    }
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

    contents = new ImagePanel() {
      imagePath = "src/main/images/Background.png"

      contents += new GridPanel(3, 1) {
        opaque = false
        contents += createPlayerGrid()
        contents += createCardGrid
        contents += buttonFlowPanel
      }
    }
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


  def saveHelpSmall(): Unit = {
    saveGameButton.preferredSize = new Dimension(100, 50)
    helpButton.preferredSize = new Dimension(100, 50)
  }

  def saveHelpBig(): Unit = {
    saveGameButton.preferredSize = new Dimension(200, 100)
    helpButton.preferredSize = new Dimension(200, 100)
  }


}

