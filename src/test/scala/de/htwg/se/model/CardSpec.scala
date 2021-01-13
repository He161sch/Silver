package de.htwg.se.model

import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardSpec extends AnyWordSpec with Matchers {
  "A Card" when {
    "new" should {
      val card = Card(false, 3)
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