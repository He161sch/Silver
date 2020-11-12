package model


import aview.TUI
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI" when {
    "new" should {
      val tui = new TUI
      val hand = Hand(List(Card(0), Card(2), Card(2)))
      val player = Player("Player", Hand(List(Card(0), Card(2), Card(2))))
      val idx = 0
      val idx1 = 1
      val idx2 = 2
      val newCard = Card(3)
      "switch Cards of the Player" in{
        tui.switchCard(player, idx1, newCard) should be (Player("Player", Hand(List(Card(0), Card(3), Card(2)))))
      }
      "remove a Card" in{
        tui.removeAtIdx(1, hand.cards) should be (List(Card(0), Card(2)))
      }
      "can combine cards" in {
        tui.combineCard(player, idx1, idx2, newCard) should be (Player("Player", Hand(List(Card(0), Card(3)))))
      }
      "can't combine wrongs cards" in {
        tui.combineCard(player, idx, idx2, newCard) should be (Player("Player", Hand(List(Card(0), Card(2), Card(2)))))
      }
      "switch card with s <0-4>" in {
        tui.processInputLine("s 1", player, newCard) should be (Player("Player", Hand(List(Card(0), Card(3), Card(2)))))
      }
      "switch two cards with c <0-4> <0-4>" in {
        tui.processInputLine("c 1 2", player, newCard) should be (Player("Player", Hand(List(Card(0), Card(3)))))
      }
      "see the value of the card with d <0-4>" in {
        tui.processInputLine("d 0", player, newCard) should be (Player("Player", Hand(List(Card(0), Card(2), Card(2)))))
      }
      "end the game with e" in {
        tui.processInputLine("e", player, newCard) should be (System.exit(0))
      }
    }
  }
}