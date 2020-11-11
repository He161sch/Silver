
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Player", Hand(List(Card(1))))
      "have a name" in {
        player.name should be("Player")
      }
      "have this String representation" in {
        player.toString should be("Player")
      }
      "have a random hand" in {
        player.hand should be (Hand(List(Card(1))))
      }
      "have a card" in {
        player.randomCard() should be (1)
      }
      "when unapplied" in {
        Player.unapply(player).get should be ("Player", Hand(List(Card(1))))
      }
    }
  }
}