package model

import model.cardcomponent.cardbaseimlp.Card
import model.handcomponent.handbaseimpl.Hand
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
      "have a HandValue of" in {
        hand.handValue() should be (4)
      }
      "have a Card removed" in {
        hand.removeAtIdx(1, hand.cards)  should be (Vector(Card(1)))
      }
      "have unapply" in {
        Hand.unapply(hand).get should be (Vector(Card(1), Card(3)))
      }
    }
  }
}