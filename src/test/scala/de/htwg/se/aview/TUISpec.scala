package de.htwg.se.aview

import java.io.ByteArrayOutputStream
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.controller.controllercomponent.GameState._
import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.playercomponent.PlayerInterface
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class TUISpec extends AnyWordSpec with Matchers {
  "A TUI" should {
    "process command 'z' at WelcomeState " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      controller.gameState = WelcomeState
      tui.processCommands("z")
      controller.gameState should be (WelcomeState)
    }
    "process command 'y' at WelcomeState " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      controller.gameState = WelcomeState
      tui.processCommands("y")
      controller.gameState should be (WelcomeState)
    }
    "process command 'z' at DRAWEDCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("d")
      tui.processCommands("z")
      controller.gameState should be (InputName)
    }
    "process command 'y' at DRAWEDCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("d")
      tui.processCommands("y")
      controller.gameState should be (DRAWEDCARD)
    }
//    "get the state" in {
//      tui.processInputLine("state") should be(controller.getState())
//    }
//    "not a right input" in {
//      tui.processInputLine("a") should be(println("unknown command ... Try again"))
//    }
    "should have this output with the WelcomeState case" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)
      val out = new ByteArrayOutputStream();
      Console.withOut(out) {
        tmpController.gameState = WelcomeState
        tui.update
      }
      out.toString should be("Welcome to Silver :)\nHow many players want to play[2 or 3]?\r\n")
    }
    "should have this output with the InputName case" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)
      val out = new ByteArrayOutputStream();
      Console.withOut(out) {
        tmpController.gameState = InputName
        tui.update
      }
      out.toString should be("Please enter Playername 1:\r\n")
    }
    "should have this output with the NEWGAMESTART case" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)
      val out = new ByteArrayOutputStream();
      Console.withOut(out) {
        tmpController.gameState = NEWGAME
        tui.update
      }
      out.toString should be("A new Game started ... Deck is now shuffeled!\r\n")
    }
//      "should have this output with the PLAYER_TURN case" in {
//        val deck = new Deck()
//        var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
//        val tmpController = new Controller(gameConfig)
//        val tui = new TUI(tmpController)
//        val out = new ByteArrayOutputStream();
//        tui.processCommands("2")
//        tui.processCommands("SE1")
//        tui.processCommands("SE2")
//        Console.withOut(out){
//          tmpController.gameState = PLAYER_TURN
//          tui.update
//        }
//        val builder = new StringBuilder();
//        out.toString should be (builder.append("SE1's turn. Draw or View a Card?(d/v)\n\n")
//                                .append(tmpController.gameStateToString).append("\n").toString())
//      }
  }
}