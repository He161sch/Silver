
package model


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HandSpec extends AnyWordSpec with Matchers {
  "A Hand" when {
    "new" should {
      val hand = Hand(Vector(Card(1), Card(3)))
      "have cards" in {
        hand.cards should be(Vector(Card(1), Card(3)))
      }
      "have a nice String representation" in{
        hand.toString should be("[1, 3]\n")
      }
      "when unapplied" in {
        Hand.unapply(hand).get should be (Vector(Card(1), Card(3)))
      }
    }
  }

}
