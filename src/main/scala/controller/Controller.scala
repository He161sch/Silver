package controller

import model.{Card, Deck, GameConfig, Hand, Player}
import util.{Observable, UndoManager}

import scala.swing.Publisher
import scala.util.Random
import scala.util.control.Exception.allCatch

object GameState extends Enumeration{
  val  WelcomeState, InputName, PLAYER_TURN, NEWGAMESTART, DRAWEDCARD, SWITCHCARD, COMBINECARD, VIEWCARD,
        PlayerWon, EndGame = Value
}


import GameState._

class Controller() extends Publisher {

  var deck = new Deck
  var gameState = WelcomeState
  var running: State = IsNotRunning()
  var gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0)
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
    publish(new updateData)
  }
  def performViewCard(idx: Int): Unit = {
    undoManager.doStep(new CommandViewCard(this, idx))
    publish(new updateData)
  }
  def performSwitchCard(idx: Int): Unit = {

    undoManager.doStep(new CommandSwitchCard(this, idx))
    publish(new updateData)
  }
  def performCombineCard(idx1: Int, idx2: Int): Unit = {
    undoManager.doStep(new CommandCombineCard(this, idx1, idx2))
    publish(new updateData)
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
    publish(new updateData)
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
    nextPlayer()
  }

  def nextPlayer(): Unit = {
    gameConfig = gameConfig.incrementActivePlayerIdx()

    gameState = PLAYER_TURN

    if (gameConfig.activePlayerIdx >= gameConfig.players.size) {
      gameConfig = gameConfig.resetActivePlayerIdx()

    }
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

  // Nach dem Ziehen kann man auch keine Karte tauschen
}
