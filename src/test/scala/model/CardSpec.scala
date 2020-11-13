package model


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardSpec extends AnyWordSpec with Matchers {
  "A Card" when {
    "new" should {
      val card = Card(3)
      "have a number" in{
        card.number should be(3)
      }
      "have a nice String representation" in{
        card.toString should be("3")
      }
      "have unapply" in {
        Card.unapply(card).get should be (3)
      }
    }
  }
}