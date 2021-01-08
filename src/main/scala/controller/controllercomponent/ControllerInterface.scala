package controller.controllercomponent

import model.gameconfigcomponent.gameconfigbaseimpl
import model.deckcomponent.deckbaseimpl.Deck
import model.playercomponent.playerbaseimpl.Player

import util.UndoManager

import scala.swing.Publisher

object GameState extends Enumeration{
  val  WelcomeState, InputName, PLAYER_TURN, NEWGAMESTART, DRAWEDCARD, SWITCHCARD, COMBINECARD, VIEWCARD,
        PlayerWon, EndGame = Value
}


import controller.controllercomponent.GameState._

trait ControllerInterface extends Publisher {

  var deck = new Deck
  var gameState = WelcomeState
  var running: State = IsNotRunning()
  var gameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](), deck.resetDeck(), 0)
  private val undoManager = new UndoManager

  def getState(): Unit

  def performInitGame(playerAmount: Int): Unit

  def performSetPlayerName(playerName: String): Unit

  def performViewCard(idx: Int): Unit

  def performSwitchCard(idx: Int): Unit

  def performCombineCard(idx1: Int, idx2: Int): Unit

  def initGame(playeramount: Int): Unit

  def getActivePlayerName: String

  def getPlayerName: String

  def setPlayerName(playerName: String): Unit

  def viewCard(): Unit

  def viewCard(idx: Int): Unit

  def drawCard(): Unit

  def printdrawedCard(): String

  def switchCard(idx: Int): Unit

  def combineCard(idx1: Int, idx2: Int): Unit

  def nextPlayer(): Unit

  def whoWon(): Unit

  def quitGame(): Unit

  def gameStateToString: String

  def undoStep: Unit

  def redoStep: Unit

  def mapSymbolToChar(hidePlayerCards: Boolean): List[String]

  def mapDrawedCard(hidePlayerCards: Boolean): List[String]
}
