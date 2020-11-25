package model


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.Observer

class PlayerTest extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Player", Hand(List(Card(1))))
      "have a name" in {
        player.name should be("Player")
      }
      "have this String representation" in {
        player.toString should be("Player's hand: " + Hand(List(Card(1))))
      }
      "get the Player" in {
        player.getPlayer.toString should be ("Player's hand: " + Hand(List(Card(1))))
      }
      "have a hand" in {
        player.hand should be (Hand(List(Card(1))))
      }
      "have a card" in {
        player.randomCard() canEqual (equal (Card(0))
          or equal (Card(1))
          or equal (Card(2))
          or equal (Card(3))
          or equal (Card(4))
          or equal (Card(5))
          or equal (Card(6))
          or equal (Card(7))
          or equal (Card(8))
          or equal (Card(9))
          or equal (Card(10))
          or equal (Card(11))
          or equal (Card(12))
          or equal (Card(13))
          )
      }
      "when unapplied" in {
        Player.unapply(player).get should be ("Player", Hand(List(Card(1))))
      }
    }
  }
}
