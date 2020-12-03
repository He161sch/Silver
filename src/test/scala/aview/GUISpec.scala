package aview

import controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GUISpec extends AnyWordSpec with Matchers{
  "Gui" should {
    val controller = new Controller()
    val gui = new GUI(controller)
    "should have this String representation when updated" in {

    }

  }
}
