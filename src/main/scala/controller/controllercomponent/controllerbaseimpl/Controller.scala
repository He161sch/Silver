package controller.controllercomponent.controllerbaseimpl

import controller.controllercomponent.GameState.{COMBINECARD, DRAWEDCARD, EndGame, InputName, NEWGAMESTART, PLAYER_TURN, PlayerWon, SWITCHCARD, VIEWCARD, WelcomeState}
import controller.controllercomponent._
import model.{Deck, GameConfig, Player}
import util.UndoManager

import scala.swing.Publisher


class Controller() extends ControllerInterface with Publisher{

  // Überflüssig
  // var deck = new Deck
  // var gameState = WelcomeState
  // var running: State = IsNotRunning()
  // var gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0)

  private val undoManager = new UndoManager

  def getState(): Unit = {
    val (state, output) = running.handle(running)
    running = state
    println(output)
  }

  def performInitGame(playerAmount: Int): Unit = {
    undoManager.doStep(new CommandPlayerAmount(this, playerAmount))
    publish(new updateData)
  }
  def performSetPlayerName(playerName: String): Unit = {
    undoManager.doStep(new CommandInputNames(this, playerName))
  }
  def performViewCard(idx: Int): Unit = {
    undoManager.doStep(new CommandViewCard(this, idx))
  }
  def performSwitchCard(idx: Int): Unit = {

    undoManager.doStep(new CommandSwitchCard(this, idx))

  }
  def performCombineCard(idx1: Int, idx2: Int): Unit = {
    undoManager.doStep(new CommandCombineCard(this, idx1, idx2))
  }

  def initGame(playeramount: Int): Unit = {
    for (_ <- 1 to playeramount) {
      gameConfig = gameConfig.createPlayer()
    }

    gameState = InputName
    running = IsRunning()
  }


  def getActivePlayerName: String = gameConfig.getActivePlayerName

  def getPlayerName: String = {
    "Please enter Playername " + {gameConfig.activePlayerIdx + 1}+ ":"
  }


  def setPlayerName(playerName: String): Unit = {
    gameConfig = gameConfig.setPlayerName(playerName, gameConfig.activePlayerIdx)
    gameConfig = gameConfig.incrementActivePlayerIdx()

    if (gameConfig.activePlayerIdx >= gameConfig.players.size) {
      gameConfig = gameConfig.resetActivePlayerIdx()
      gameState = NEWGAMESTART
      publish(new updateData)
      gameState = PLAYER_TURN
    }
  }

  def viewCard(): Unit = {
    gameState = VIEWCARD
  }
  def viewCard(idx: Int): Unit = {
    gameConfig = gameConfig.viewCard(idx)
    publish(new updateData)
    nextPlayer()
  }

  def drawCard(): Unit = {
    gameConfig = gameConfig.drawCard()
    gameState = DRAWEDCARD
    publish(new updateData)
  }

  def printdrawedCard(): String = {
    "" + gameConfig.players(gameConfig.activePlayerIdx).newCard + ""
  }

  def switchCard(idx: Int): Unit = {
    gameConfig = gameConfig.switchCard(idx)
    gameState = SWITCHCARD
    publish(new updateData)
    nextPlayer()
  }

  def combineCard(idx1: Int, idx2: Int): Unit = {
    gameConfig = gameConfig.combineCard(idx1, idx2)
    gameState = COMBINECARD
    publish(new updateData)
    nextPlayer()
  }

  def nextPlayer(): Unit = {
    gameConfig = gameConfig.incrementActivePlayerIdx()

    gameState = PLAYER_TURN

    if (gameConfig.activePlayerIdx >= gameConfig.players.size) {
      gameConfig = gameConfig.resetActivePlayerIdx()

    }
    publish(new updateData)
  }

  def whoWon(): Unit = {
    var closestValue = 0
    for (i <- gameConfig.players.indices) {
      val handValue = gameConfig.players(i).hand.handValue()
      if (handValue >= 0) {
        closestValue = handValue
      }
    }
    for (i <- gameConfig.players.indices) {
      val handValue = gameConfig.players(i).hand.handValue()
      if (handValue == closestValue) {
        gameConfig = gameConfig.addWinner(gameConfig.players(i))
      }
    }
    gameState = PlayerWon
    publish(new updateData)
    running = IsNotRunning()
    quitGame()

  }

  def quitGame(): Unit = {
    gameState = EndGame
    publish(new updateData)
  }


  def gameStateToString: String = {
    gameState match {
      case PLAYER_TURN | VIEWCARD | SWITCHCARD | COMBINECARD => gameConfig.getActivePlayer.toString
      case PlayerWon => gameConfig.winnerToString()
    }
  }
  def undoStep: Unit = {
    undoManager.undoStep
    publish(new updateData)
  }
  def redoStep: Unit = {
    undoManager.redoStep
    publish(new updateData)
  }


  def mapSymbolToChar(hidePlayerCards: Boolean): List[String] = {
    var cardImageNames = List[String]()

    for (card <- gameConfig.getActivePlayer.hand.cards) {
      cardImageNames = cardImageNames :+ (card.toString + ".png")
    }
    cardImageNames
  }

  def mapDrawedCard(hidePlayerCards: Boolean): List[String] = {
    var cardImageNames = List[String]()

    cardImageNames = cardImageNames :+ (gameConfig.getActivePlayer.newCard.toString + ".png")

    cardImageNames
  }

  // Nach dem Ziehen kann man auch keine Karte tauschen
}
