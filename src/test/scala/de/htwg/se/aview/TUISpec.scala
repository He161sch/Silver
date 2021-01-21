package de.htwg.se.aview

import java.io.{ByteArrayOutputStream, StringReader}
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.controller.controllercomponent.GameState._
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.PlayerInterface
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
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
    "notice user to enter correct number of players" in {
      val out = new ByteArrayOutputStream();
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)
      Console.withOut(out) {
        tui.initPlayers("1")
      }
      out.toString should include("You can only play with 2 or 3 Players\n. . .\nTry again")
    }

    "process command 'z' at InputName " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      controller.gameState = InputName
      tui.processCommands("z")
      controller.gameState should be (InputName)
    }
    "process command 'y' at InputName " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      controller.gameState = InputName
      tui.processCommands("y")
      controller.gameState should be (InputName)
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
    "process command 'c 0 1' at DRAWEDCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE1", Hand(Vector(Card(false, 0), Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("d")
      tui.processCommands("c 0 1")
      controller.gameState should be (PLAYER_TURN)
    }
    "process command '_' at DRAWEDCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("d")
      tui.processCommands("a")
      controller.gameState should be (DRAWEDCARD)
    }
    "process command 'z' at VIEWCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("v")
      tui.processCommands("z")
      controller.gameState should be (InputName)
    }
    "process command 'y' at VIEWCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("v")
      tui.processCommands("y")
      controller.gameState should be (VIEWCARD)
    }
    "process command 'v 0' at VIEWCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE", Hand(Vector(Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("v")
      tui.processCommands("v 0")
      controller.gameState should be (PLAYER_TURN)
    }
    "process command '_' at VIEWCARD " in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE", Hand(Vector(Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val controller = new Controller(gameConfig)
      val tui = new TUI(controller)
      tui.processCommands("2")
      tui.processCommands("SE1")
      tui.processCommands("SE2")
      tui.processInputLine("v")
      tui.processCommands("a")
      controller.gameState should be (VIEWCARD)
    }
    "have this output on FALSECOMMAND" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE", Hand(Vector(Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 1, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)
      val out = new ByteArrayOutputStream();
      Console.withOut(out){
        tmpController.gameState = FALSECOMMAND
        tui.update
      }
      val builder = new StringBuilder();
      out.toString should be (builder.append("Command isn't allowed\n").toString())
    }
    "validate switch action" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE", Hand(Vector(Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)

      tui.validateSwitch(0) should be (Some(tmpController.performSwitchCard(0)))
    }
    "validate switch action ... gone wrong :D" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE", Hand(Vector(Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)

      tui.validateSwitch(6) should be (None)
    }
    "run" in {
      val deck = new Deck()
      var gameConfig = GameConfig(Vector[PlayerInterface](Player("SE", Hand(Vector(Card(false, 0))), Card(false, 14))), deck.resetDeck(), Card(false, 15), 0, Vector[PlayerInterface]())
      val tmpController = new Controller(gameConfig)
      val tui = new TUI(tmpController)
      val in = new StringReader("q")
      val out = new ByteArrayOutputStream()
      Console.withIn(in) {
        Console.withOut(out) {
          tui.run()
          val array = out.toString().split("\r?\n")
        }
      }
    }
  }
}