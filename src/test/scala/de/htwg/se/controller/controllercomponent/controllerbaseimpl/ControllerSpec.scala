package de.htwg.se.controller

import java.io.ByteArrayOutputStream

import de.htwg.se.controller.controllercomponent.{GameState, IsNotRunning, IsRunning}

import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import de.htwg.se.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "new" should {
      val controller = new Controller(new GameConfig())
      val deck = new Deck

      "when game is Running" in {
        controller.running = IsRunning()

        val out = new ByteArrayOutputStream();
        Console.withOut(out) {
          controller.getState()
        }
        out.toString should include ("Game is running!")
      }
      "when game is not Running" in {
        controller.running = IsNotRunning()

        val out = new ByteArrayOutputStream();
        Console.withOut(out) {
          controller.getState()
        }
        out.toString should include ("Game is not running!")
      }
      "get player name" in {
        controller.gameConfig = GameConfig(Vector[Player](), deck.resetDeck(),Card(false, 15), 1, Vector[Player]())
        controller.getPlayerName should be("Please enter Playername 2:")
      }
      "view a card" in {
        controller.gameConfig = GameConfig(Vector[Player](Player("moin", Hand(Vector(Card(false, 1))), Card(false, 1))), deck.resetDeck(),Card(false, 15), 0, Vector[Player]())
        controller.viewCard(0)
        controller.gameConfig.getAllPlayers(0).getHand.getCard(0).getVisibility should be (true)
        controller.viewCard()
        controller.gameState should be (GameState.VIEWCARD)
      }

    }
  }
}