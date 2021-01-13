package de.htwg.se.model

import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Player", Hand(Vector(Card(false, 1))), Card(true, 0))
      "have a name" in {
        player.name should be("Player")
      }
      "have this String representation" in {
        player.toString should be("Player's hand: " + Hand(Vector(Card(false, 1))))
      }
      "get the Player" in {
        player.getPlayer.toString should be ("Player's hand: " + Hand(Vector(Card(false, 1))))
      }
      "have a hand" in {
        player.hand should be (Hand(Vector(Card(false, 1))))
      }
      "get the hand" in {
        player.getHand should be (Hand(Vector(Card(false, 1))))
      }
      "get the Name" in {
        player.getName should be ("Player")
      }
      "get the new Card" in {
        player.getNewCard should be (Card(true, 0))
      }
      "when unapplied" in {
        Player.unapply(player).get should be ("Player", Hand(Vector(Card(false, 1))), Card(true, 0))
      }
    }
  }

}
