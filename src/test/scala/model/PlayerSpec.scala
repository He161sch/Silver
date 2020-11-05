package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers{
    "A Player" when { "new" should {
      var player = Player("Player1")
      "have a name" in {
        player.name should be("Player1")
      }
      "have this String representation" in {
        player.toString should be ("Player1")
      }
    }}
}
