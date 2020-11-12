package model


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HandSpec extends AnyWordSpec with Matchers {
  "A Hand" when {
    "new" should {
      val hand = Hand(List(Card(1), Card(3)))
      "have cards" in {
        hand.cards should be(List(Card(1), Card(3)))
      }
      "have a nice String representation" in{
        hand.toString should be("[1, 3]\n")
      }
      "have a HandValue of" in {
        hand.handValue() should be (4)
      }
      "when unapplied" in {
        Hand.unapply(hand).get should be (List(Card(1), Card(3)))
      }
    }
  }
}
