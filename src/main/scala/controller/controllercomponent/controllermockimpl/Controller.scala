package controller.controllercomponent.controllermockimpl


import controller.controllercomponent.{CommandCombineCard, CommandInputNames, CommandPlayerAmount, CommandSwitchCard, CommandViewCard, ControllerInterface, IsNotRunning, IsRunning, State, updateData}
import model.{Deck, GameConfig, Player}
import util.UndoManager

import scala.swing.Publisher

case class Controller() extends ControllerInterface {

  // Überflüssig
  // var deck = new Deck
  // var gameState = WelcomeState
  // var running: State = IsNotRunning()
  // var gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0)
  private val undoManager = new UndoManager

  def getState(): Unit = {}

  def performInitGame(playerAmount: Int): Unit = {}

  def performSetPlayerName(playerName: String): Unit = {}

  def performViewCard(idx: Int): Unit = {}

  def performSwitchCard(idx: Int): Unit = {}

  def performCombineCard(idx1: Int, idx2: Int): Unit = {}

  def initGame(playeramount: Int): Unit = {}

  def getActivePlayerName: String = ""

  def getPlayerName: String = ""

  def setPlayerName(playerName: String): Unit = {}

  def viewCard(): Unit = {}

  def viewCard(idx: Int): Unit = {}

  def drawCard(): Unit = {}

  def printdrawedCard(): String = ""

  def switchCard(idx: Int): Unit = {}

  def combineCard(idx1: Int, idx2: Int): Unit = {}

  def nextPlayer(): Unit = {}

  def whoWon(): Unit = {}

  def quitGame(): Unit = {}

  def gameStateToString: String = ""

  def undoStep: Unit = {}

  def redoStep: Unit = {}

  def mapSymbolToChar(hidePlayerCards: Boolean): List[String] = List("")

  def mapDrawedCard(hidePlayerCards: Boolean): List[String] = List("")

}