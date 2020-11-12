package model


import aview.TUI
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI" when {
    "new" should {
      val tui = new TUI
      val hand = Hand(List(Card(0), Card(1), Card(2)))
      val player = Player("Player", Hand(List(Card(0), Card(2), Card(2))))
      val idx = 1
      val newCard = Card(3)
      "switch Cards of the Player" in{
        tui.switchCard(player, idx, newCard) should be (Player("Player", Hand(List(Card(0), Card(3), Card(2)))))
      }
      "remove a Card" in{
        tui.removeAtIdx(1, hand.cards) should be (List(Card(0), Card(2)))
      }
      "switch card with s <1-5>" in {
        tui.processInputLine("s 1", player, newCard) should be (Player("Player", Hand(List(Card(0), Card(3), Card(2)))))
      }
      "combine cards if same value" in {
        tui.combineCard(player, 1, 2, newCard) should be (Player("Player", Hand(List(Card(0), newCard))))
      }
      "dont combine cards if different value" in {
        tui.combineCard(player, 0, 1, newCard) should be (player)
      }
      "check value of card with d" in {
        tui.processInputLine("d 1", player, newCard) should be (player)
      }
      "combine 2 cards with c <0-4> <0-4>" in{
        tui.processInputLine("c 0 1", player, newCard) should be (player)
      }
      "end game with e" in {
        tui.processInputLine("e", player, newCard) should be (System.exit(0))
      }

    }
  }
}