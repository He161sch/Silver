package model


import aview.TUI
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI" when {
    "new" should {
      val tui = new TUI
      val hand = Hand(List(Card(0), Card(1), Card(2)))
      val player = Player("Player", Hand(List(Card(1))))
      val idx = 1
      val newCard = player.randomCard()
      "switch Cards of the Player" in{
        tui.switchCard(player, idx, newCard).toString should be ("Player")
      }
      "remove a Card" in{
        tui.removeAtIdx(1, hand.cards) should be (List(Card(0), Card(2)))
      }
      "can't be seen" in {
        tui.processInputLine("s", player, newCard) should be (1)
      }
      "have unapply" in {

      }
    }
  }
}