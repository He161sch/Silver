package aview

import java.io.ByteArrayOutputStream

import controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GUISpec extends AnyWordSpec with Matchers{
  "Gui" should {
    val controller = new Controller()
    val gui = new GUI(controller)
    "should have this String representation when updated" in {
      val output = new ByteArrayOutputStream()
      Console.withOut(output){
        controller.ObserverInState()
      }
      output.toString should include ("Try it with TUI again")
    }

    "should have this output when command is processed" in {
      gui.inputCommand("some input")
      val output = new ByteArrayOutputStream()
      Console.withOut(output){
        controller.ObserverInState()
      }
      output.toString should include ("Try it with TUI again")
    }

  }
}
