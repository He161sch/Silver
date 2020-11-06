package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec with Matchers {
  "A Player1" when {
    "new" should {
      var player1 = Player("Player1")
      "have a name" in {
        player1.name should be("Player1")
      }
      "have this String representation" in {
        player1.toString should be("Player1")
      }
      "when unapplied" in {
        Player.unapply(player1).get should be ("Player1")
      }
    }
  }
}
