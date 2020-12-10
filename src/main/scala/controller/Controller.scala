package controller

import model.{Card, Deck, GameConfig, Hand, Player}
import util.{Observable, UndoManager}

import scala.util.Random
import scala.util.control.Exception.allCatch

object GameState extends Enumeration{
  val  WelcomeState, InputName, PLAYER_TURN, NEWGAMESTART, DRAWEDCARD, SWITCHCARD, COMBINECARD, VIEWCARD,


  roundStarted, gameOver, nextPlayerCard, playersChoice ,roundOver,
  ShowHandValue, switchOrCombineCard, drawViewCard = Value
}


import GameState._

class Controller() extends Observable {

  var deck = new Deck
  var gameState = WelcomeState
  var running: State = IsNotRunning()
  var gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0, Vector[Player]())
  private val undoManager = new UndoManager

  def getState(): Unit = {
    val (state, output) = running.handle(running)
    running = state
    println(output)
  }

  def performInitGame(playerAmount: Int): Unit = {
    undoManager.doStep(new CommandPlayerAmount(this, playerAmount))
    notifyObservers
  }
  def performSetPlayerName(playerName: String): Unit = {
    undoManager.doStep(new CommandInputNames(this, playerName))
    notifyObservers
  }
  def performViewCard(idx: Int): Unit = {
    undoManager.doStep(new CommandViewCard(this, idx))
    notifyObservers
  }
  def performSwitchCard(idx: Int): Unit = {

    undoManager.doStep(new CommandSwitchCard(this, idx))
    notifyObservers
  }
  def performCombineCard(idx1: Int, idx2: Int): Unit = {
    undoManager.doStep(new CommandCombineCard(this, idx1, idx2))
    notifyObservers
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
      notifyObservers
      gameState = PLAYER_TURN
    }
  }

  def viewCard(): Unit = {
    gameState = VIEWCARD
    notifyObservers
  }
  def viewCard(idx: Int): Unit = {
    gameConfig = gameConfig.viewCard(idx)
    notifyObservers
    nextPlayer()
  }

  def drawCard(): Unit = {
    gameConfig = gameConfig.drawCard()
    gameState = DRAWEDCARD
    notifyObservers
  }

  def printdrawedCard(): String = {
    "" + gameConfig.players(gameConfig.activePlayerIdx).newCard + ""
  }

  def switchCard(idx: Int): Unit = {
    gameConfig = gameConfig.switchCard(idx)
    gameState = SWITCHCARD
  }

  def combineCard(idx1: Int, idx2: Int): Unit = {
    gameConfig = gameConfig.combineCard(idx1, idx2)
    gameState = COMBINECARD
    nextPlayer()
  }

  def nextPlayer(): Unit = {
    gameConfig = gameConfig.incrementActivePlayerIdx()

    if (gameConfig.activePlayerIdx >= gameConfig.players.size) {
      gameState = PLAYER_TURN
      gameConfig.resetActivePlayerIdx()
      notifyObservers
    }
  }


  def gameStateToString: String = {
    gameState match {
      case PLAYER_TURN | SWITCHCARD | COMBINECARD | VIEWCARD => gameConfig.getActivePlayer.toString
    }
  }
  def undoStep: Unit = {
    undoManager.undoStep
    notifyObservers
  }
  def redoStep: Unit = {
    undoManager.redoStep
    notifyObservers
  }

  def validIndex(input: String): Option[String] = {
    if(input.toInt > 4) None
    else Some(input)
  }


}
