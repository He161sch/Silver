package aview

import controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.Observer

class TUISpec extends AnyWordSpec with Matchers {
  "A TUI" when {
    "new" should {
      val controller = new Controller()
      val tui = new TUI(controller)

      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "draw a Card" in{

        tui.processInputLine("d") should be (controller.drawCard())
        observer.updated should be(true)
      }
      "view a Card" in{
        tui.processInputLine("v 1") should be (controller.viewCard(1))
        observer.updated should be(true)
      }
      "switch card with s <0-4>" in {
        tui.processInputLine("s 1") should be (controller.switchCard(1))
        observer.updated should be(true)
      }
      "combine 2 cards with c <0-4> <0-4>" in {
        tui.processInputLine("c 0 1") should be (controller.combineCard(0, 1))
        observer.updated should be(true)
      }
      "not a right input" in {
        tui.processInputLine("a") should be (println("ungültiger befehl"))
        observer.updated should be(true)
      }
      "end game with e" in {
        tui.processInputLine("e") should be (System.exit(0))
      }
    }
  }
}