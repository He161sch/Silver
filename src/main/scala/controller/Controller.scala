package controller

import model.{Card, Hand, Player, Deck, GameConfig}
import util.{Observable, UndoManager}

import scala.util.Random

object GameState extends Enumeration{
  val  WELCOMESTATE, NAME_CREATION, PLAYER_TURN,


  gameStarted, getAmount, playerCreate, roundStarted, gameOver, nextPlayerCard, playersChoice ,roundOver,
  ViewCard, SwitchCard, ShowHandValue, switchOrCombineCard, drawViewCard = Value
}


import GameState._

class Controller(var deck: Deck) extends Observable {

  var gameState = WELCOMESTATE
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

  def initGame(playeramount: Int): Unit = {
    for (_ <- 1 to playeramount) {
      gameConfig = gameConfig.createPlayer()
    }

    gameState = NAME_CREATION
    running = IsRunning()
  }

  def getActivePlayerName: String = gameConfig.getActivePlayerName

  def getPlayerName: String = {
    "Please enter Playername " + {gameConfig.activePlayerIdx + 1}+ ":"
  }


  def setPlayerName(playerName: String): Unit = {
    gameConfig = gameConfig.setPlayerName(playerName, gameConfig.activePlayerIdx)
    gameConfig = gameConfig.incrementActivePlayerIdx()

//    if (gameConfig.activePlayerIdx >= gameConfig.players.size) {
//      gameConfig = gameConfig.resetActivePlayerIdx()
//    }
  }

  def drawCard(): Unit = {
    deck.drawCards(1)
  }

  def combineCard(): Unit = {

  }


  def gameStateToString: String = {
    gameState match {
      case PLAYER_TURN => gameConfig.getActivePlayer.toString
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

}
