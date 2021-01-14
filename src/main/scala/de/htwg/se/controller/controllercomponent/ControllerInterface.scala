package de.htwg.se.controller.controllercomponent

import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.controller.controllercomponent.GameState.{WelcomeState, GameState}
import scala.swing.Publisher


object GameState extends Enumeration{
  type GameState = Value
  val  WelcomeState, InputName, PLAYER_TURN, NEWGAME, DRAWEDCARD, SWITCHCARD, COMBINECARD, VIEWCARD, FALSECOMMAND, IDLE,
  PlayerWon, EndGame = Value
}


trait ControllerInterface extends Publisher {

  var gameConfig: GameConfigInterface
  var gameState: GameState = WelcomeState
  //  var deck = new Deck
  //  va
  //  var running: State = IsNotRunning()
  //  var gameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](), deck.resetDeck(), 0)
  //  private val undoManager = new UndoManager

  def getState(): Unit
  def performInitGame(playerAmount: Int): Unit
  def performSetPlayerName(playerName: String): Unit
  def performViewCard(idx: Int): Unit
  def performSwitchCard(idx: Int): Unit
  def performCombineCard(idx1: Int, idx2: Int): Unit
  def initGame(playeramount: Int): Unit
  def getPlayerName: String
  def setPlayerName(playerName: String): Unit
  def viewCard(): Unit
  def viewCard(idx: Int): Unit
  def drawCard(): Unit
  def drawFromDiscard(): Unit
  def printdrawedCard(): String
  def switchCard(idx: Int): Unit
  def combineCard(idx1: Int, idx2: Int): Unit
  def nextPlayer(): Unit
  def discardCard(): Unit
  def whoWon(): Unit
  def quitGame(): Unit
  def gameStateToString: String
  def undoStep: Unit
  def redoStep: Unit
  def mapSymbolToChar(hidePlayerCards: Boolean): List[String]
  def mapDrawedCard(hidePlayerCards: Boolean): List[String]
  def mapDiscardCard(hidePlayerCards: Boolean):  List[String]
  def save: Unit
  def load: Unit
  def newGame: Unit
}
