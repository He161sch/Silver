package model

import model.cardcomponent.cardbaseimlp.Card
import model.handcomponent.handbaseimpl.Hand
import model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Player", Hand(Vector(Card(1))), Card(0))
      "have a name" in {
        player.name should be("Player")
      }
      "have this String representation" in {
        player.toString should be("Player's hand: " + Hand(Vector(Card(1))))
      }
      "get the Player" in {
        player.getPlayer.toString should be ("Player's hand: " + Hand(Vector(Card(1))))
      }
      "have a hand" in {
        player.hand should be (Hand(Vector(Card(1))))
      }
      "when unapplied" in {
        Player.unapply(player).get should be ("Player", Hand(Vector(Card(1))), Card(0))
      }
    }
  }

}
