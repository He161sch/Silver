package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameConfigSpec extends AnyWordSpec with Matchers {
  "A GameConfig" when {
    "new" should {
      val deck = Deck(Vector(Card(0),Card(1), Card(2), Card(3), Card(4), Card(5), Card(6), Card(7), Card(8),
                      Card(9), Card(10), Card(11), Card(12), Card(13)))

      val gameConfig = GameConfig(Vector[Player](), deck: Deck, 0, Vector[Player]())

      "create Player with default name" in {
        val config = gameConfig.createPlayer()
        config.players(0).name should be ("")
      }

      "create Player" in {
        val config = gameConfig.createPlayer("SETest")
        config.players(0).name should be ("SETest")
        config.players(0).hand.cards.size should be (5)
      }

      "set the name of the player" in {
        var config = gameConfig.createPlayer("SETest")
        config = config.setPlayerName("SE", 0)
        config.players(0).name should be ("SE")
      }

      "update PlayerIdx" in {
        val player1 = Player("SETest", Hand(Vector[Card]()), Card(0))
        var config = gameConfig.createPlayer("SE1")
        config = config.createPlayer("SE2")
        config = config.updatePlayerAtIdx(player1, 0, deck)
        config.players(0).name should be ("SETest")
        config.players(0).hand.cards.size should be (0)
        config.players(1).name should be ("SE2")
        config.players(1).hand.cards.size should be (5)
      }

      "view a card" in {

      }

      "draw a card" in {

      }

      "switch a card" in {

      }

      "combine a card" in {

      }

      "increment the activePlayerIdx" in {
        val currentIdx = gameConfig.activePlayerIdx
        val config = gameConfig.incrementActivePlayerIdx()
        (config.activePlayerIdx - currentIdx) should be (1)
      }

      "reset the activePlayerIdx" in {
        val config = gameConfig.resetActivePlayerIdx()
        config.activePlayerIdx should be (0)
      }

      "get activePlayerName" in {
        val config = gameConfig.createPlayer("SETest")
        config.getActivePlayerName should be ("SETest")
      }

      "get activePlayer" in {
        val config = gameConfig.createPlayer("SETest")
        config.getActivePlayer should be (config.players(0))
      }
    }
  }
}
