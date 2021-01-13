package de.htwg.se.model

import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.playerbaseimpl
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameConfigSpec extends AnyWordSpec with Matchers {
  "A GameConfig" when {
    "new" should {
      val deck = Deck(Vector(Card(false, 0),Card(false, 1), Card(false, 2), Card(false, 3), Card(false, 4), Card(false, 5), Card(false, 6), Card(false, 7), Card(false, 8),
        Card(false, 9), Card(false, 10), Card(false, 11), Card(false, 12), Card(false, 13)))

      val gameConfig = gameconfigbaseimpl.GameConfig(Vector(Player("SETest", Hand(Vector(Card(false, 1), Card(false, 1))), Card(false, 0))), deck: Deck, Card(false, 14),0, Vector[Player]())

      "create Player with default name" in {
        val config = gameConfig.createPlayer()
        config.getPlayerAtIdx(0).getName should be ("SETest")
      }

      "create Player" in {
        val config = gameConfig.createPlayer("SETest")
        config.getPlayerAtIdx(1).getName should be ("SETest")
        config.getPlayerAtIdx(1).getHand.getAllCards.size should be (5)
      }

      "set the name of the player" in {
        var config = gameConfig.createPlayer("SETest")
        config = config.setPlayerName("SE", 0)
        config.getPlayerAtIdx(0).getName should be ("SE")
      }

      "update PlayerIdx" in {
        val player1 = playerbaseimpl.Player("SETest", Hand(Vector[Card]()), Card(false, 0))
        var config = gameConfig.createPlayer("SE1")
        config = config.createPlayer("SE2")
        config = config.updatePlayerAtIdx(player1, 0, deck)
        config.getPlayerAtIdx(0).getName should be ("SETest")
        config.getPlayerAtIdx(0).getHand.getAllCards.size should be (0)
        config.getPlayerAtIdx(2).getName should be ("SE2")
        config.getPlayerAtIdx(2).getHand.getAllCards.size should be (5)
      }

      "view a card" in {
        gameConfig.viewCard(0)
        gameConfig.players(0).getName should be ("SETest")


      }

      "draw a card" in {
        gameConfig.drawCard()
        gameConfig.deck.cardsInDeck should be (14)

      }

      "switch a card" in {
        gameConfig.switchCard(0).getPlayerAtIdx(0).getHand.getCard(0) should be (Card(false, 0))

      }

      "combine a card" in {
        gameConfig.combineCard(0, 1).getPlayerAtIdx(0).getHand should be (Hand(Vector(Card(false, 0))))

      }

      //      "increment the activePlayerIdx" in {
      //        val currentIdx = gameConfig.activePlayerIdx
      //        val config = gameConfig.incrementActivePlayerIdx()
      //        (config.activePlayerIdx - currentIdx) should be (1)
      //      }
      //
      //      "reset the activePlayerIdx" in {
      //        val config = gameConfig.resetActivePlayerIdx()
      //        config.activePlayerIdx should be (0)
      //      }
      //
      //      "get activePlayerName" in {
      //        val config = gameConfig.createPlayer("SETest")
      //        config.getActivePlayerName should be ("SETest")
      //      }
      //
      //      "get activePlayer" in {
      //        val config = gameConfig.createPlayer("SETest")
      //        config.getActivePlayer should be (config.getPlayerAtIdx(0))
      //      }
      //
      //      "addWinner" in {
      //        gameConfig.addWinner(playerbaseimpl.Player("SETest", Hand(Vector(Card(1), Card(1))), Card(0))).winners.size should be (1)
      //      }
      //      "winnerToString" in {
      //        val config = gameConfig.addWinner(playerbaseimpl.Player("SETest", Hand(Vector(Card(1), Card(1))), Card(0)))
      //        config.winnerToString() should be ("SETest has won with a total of 2 points\n")
      //      }
    }
  }
}