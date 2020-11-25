package controller

import model.{Card, Hand, Player}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "new" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      controller.p1 = Player("player1", Hand(List(Card(1), Card(2), Card(2))))
      "have a Player" in {
        controller.p1 should be(Player("player1", Hand(List(Card(1), Card(2), Card(2)))))
      }
      "have a newCard" in {
        controller.newCard should be (Card(0))
      }
      "see a card" in {
        controller.viewCard(0) should be(println(controller.p1.toString))
      }
      "switch a card" in {
        controller.switchCard(0) should be(println(Player("player1", Hand(List(Card(0), Card(2)))).toString))
      }
      "show value of hand" in {
        controller.showHandValue() should be (println("3"))
      }
      "combine 2 cards with same value" in{
        controller.combineCard(1, 2) should be(println("player1's hand = [1, 0]"))
      }
      "return notice if index out of bounds" in {
        controller.combineCard(2, 3) should be(println("check number of cards"))
      }
      "fail if values are not the same" in {
        controller.combineCard(0, 1) should be(println("card values are not the same! (1, 2)"))
      }
      "draw a new card" in {
        controller.drawCard() should be(println(controller.p1.toString))
      }
      "have a String representation for Hand" in {
        controller.handToString should be(Hand.toString())
      }
      "have a String representation for Player" in {
        controller.playerToString should be(Player.toString())
      }
    }
  }
}