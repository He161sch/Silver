package de.htwg.se.controller.controllercomponent.controllerbaseimpl

import com.google.inject.{Guice, Inject}
import com.malliina.audio.javasound.FileJavaSoundPlayer
import de.htwg.se.controller.controllercomponent.GameState.{COMBINECARD, DRAWEDCARD, EndGame, FALSECOMMAND, IDLE, InputName, NEWGAME, PLAYER_TURN, PlayerWon, SWITCHCARD, VIEWCARD}
import de.htwg.se.controller.controllercomponent._
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.fileiocomponent.FileIOInterface
import de.htwg.se.util.UndoManager
import de.htwg.se.SilverModule
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card

import java.nio.file.Paths
import java.util.logging.LogManager
import scala.swing.Publisher


class Controller @Inject() (var gameConfig: GameConfigInterface) extends ControllerInterface with Publisher{


  var deck = new Deck
  var running: State = IsNotRunning()
  val injector = Guice.createInjector(new SilverModule)
  val fileIO = injector.getInstance(classOf[FileIOInterface])


//  val file = Paths get "src/main/images/deckShuffle.mp3"
//  LogManager.getLogManager().reset()
//  val deckShuffle = new FileJavaSoundPlayer(file)

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
  }
  def performSwitchCard(idx: Int): Unit = {

    undoManager.doStep(new CommandSwitchCard(this, idx))

  }
  def performCombineCard(idx1: Int, idx2: Int): Unit = {
    undoManager.doStep(new CommandCombineCard(this, idx1, idx2))
  }

  def initGame(playeramount: Int): Unit = {
    for (_ <- 1 to playeramount) {
      gameConfig = gameConfig.createPlayer().asInstanceOf[GameConfig]
    }

    gameState = InputName
    running = IsRunning()
  }


  def getPlayerName: String = {
    "Please enter Playername " + {gameConfig.getActivePlayerIdx + 1}+ ":"
  }


  def setPlayerName(playerName: String): Unit = {
    gameConfig = gameConfig.setPlayerName(playerName, gameConfig.getActivePlayerIdx).asInstanceOf[GameConfig]
    gameConfig = gameConfig.incrementActivePlayerIdx().asInstanceOf[GameConfig]

    if (gameConfig.getActivePlayerIdx >= gameConfig.getPlayerSize) {
      gameConfig = gameConfig.resetActivePlayerIdx().asInstanceOf[GameConfig]
      gameState = NEWGAME
      publish(new updateData)
      gameState = PLAYER_TURN
//      deckShuffle.play()
//      deckShuffle.volume = 20
    }
  }

  def viewCard(): Unit = {
    gameState = VIEWCARD
  }
  def viewCard(idx: Int): Unit = {
    gameConfig = gameConfig.viewCard(idx).asInstanceOf[GameConfig]
    gameConfig.getActivePlayer.getHand.getCard(idx).setVisibility(true)
    publish(new updateData)
  }

  def drawCard(): Unit = {
    gameConfig = gameConfig.drawCard().asInstanceOf[GameConfig]
    gameState = DRAWEDCARD
    publish(new updateData)
  }
  def drawFromDiscard(): Unit = {
    gameConfig = gameConfig.drawFromDiscard().asInstanceOf[GameConfig]
    gameState = DRAWEDCARD
    publish(new updateData)
  }

  def printdrawedCard(): String = {
    "" + gameConfig.getPlayerAtIdx(gameConfig.getActivePlayerIdx).getNewCard + ""
  }

  def switchCard(idx: Int): Unit = {
    gameConfig.replaceDiscardDeck(idx)
    gameConfig = gameConfig.switchCard(idx).asInstanceOf[GameConfig]
    gameState = SWITCHCARD
    publish(new updateData)
    nextPlayer()
  }

  def combineCard(idx1: Int, idx2: Int): Unit = {
    if (gameConfig.getActivePlayer.getHand.getCard(idx1).getNumber != 13) {
      gameConfig.replaceDiscardDeck(idx1)
    } else {
      gameConfig.replaceDiscardDeck(idx2)
    }
    gameConfig = gameConfig.combineCard(idx1, idx2).asInstanceOf[GameConfig]
    gameState = COMBINECARD
    publish(new updateData)
    nextPlayer()
  }

  def nextPlayer(): Unit = {
    gameConfig.getActivePlayer.getHand.setAllCardsFalse
    gameConfig = gameConfig.incrementActivePlayerIdx().asInstanceOf[GameConfig]

    gameState = PLAYER_TURN

    if (gameConfig.getActivePlayerIdx >= gameConfig.getPlayerSize) {
      gameConfig = gameConfig.resetActivePlayerIdx().asInstanceOf[GameConfig]

    }
    publish(new updateData)
  }

  def discardCard(): Unit = {
    gameConfig.setDiscardDeck
    gameConfig.getActivePlayer.setNewCard
    nextPlayer()
  }

  def whoWon(): Unit = {
    var closestValue = 0
    var closestValueOld = 100
    for (i <- gameConfig.getAllPlayers.indices) {
      val handValue = gameConfig.getPlayerAtIdx(i).getHand.handValue()
      if (handValue >= 0) {
        if (handValue < closestValueOld)
          closestValue = handValue
          closestValueOld = handValue

      }
    }
    for (i <- gameConfig.getAllPlayers.indices) {
      val handValue = gameConfig.getPlayerAtIdx(i).getHand.handValue()
      if (handValue == closestValue) {
        gameConfig = gameConfig.addWinner(gameConfig.getPlayerAtIdx(i)).asInstanceOf[GameConfig]
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
      case PLAYER_TURN | VIEWCARD | SWITCHCARD | COMBINECARD => gameConfig.getAllPlayers(gameConfig.getActivePlayerIdx).toString
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

    for (card <- gameConfig.getActivePlayer.getHand.getAllCards) {

      if (!card.getVisibility) {
        cardImageNames = cardImageNames :+ (card.toString + "back.png")
      } else {
        cardImageNames = cardImageNames :+ (card.toString + ".png")
      }
    }
    cardImageNames
  }

  def mapDrawedCard(hidePlayerCards: Boolean): List[String] = {
    var cardImageNames = List[String]()

    cardImageNames = cardImageNames :+ (gameConfig.getActivePlayer.getNewCard.toString + ".png")

    cardImageNames
  }

  def mapDiscardCard(hidePlayerCards: Boolean):  List[String] = {
    var cardImageNames = List[String]()

    cardImageNames = cardImageNames :+ (gameConfig.getDiscardDeck + ".png")

    cardImageNames
  }
  def save: Unit = {
    fileIO.save(this)
    publish(new Saved)
  }

  def load: Unit = {
    val c = fileIO.load
    this.gameConfig = c.gameConfig
    this.gameState = c.gameState
    publish(new loadGame)
  }

  def newGame(): Unit ={
    if (gameState != EndGame) {
      gameState = FALSECOMMAND
      publish(new updateData)
      gameState = PLAYER_TURN
      publish(new updateData)
    } else {
      gameState = NEWGAME
      gameConfig = gameConfig.restartGame()
      publish(new newGame)
      running = IsRunning()
    }
  }
}

