package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec with Matchers{
    "A Player1" when { "new" should {
      var player1 = Player("Player1")
      "have a name" in {
        player1.name should be ("Player1")
      }
      "have this String representation" in {
        player1.toString should be ("Player1")
      }
    }}
  
  "A Player2" when { "new" should {
    var player2 = Player("Player2")
    "have a name" in {
      player2.name should be ("Player2")
    }
    "have this String representation" in {
      player2.toString should be ("Player2")
    }
  }}
}
