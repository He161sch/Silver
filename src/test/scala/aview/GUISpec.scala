package aview

import java.io.ByteArrayOutputStream

import controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GUISpec extends AnyWordSpec with Matchers{
  "A GUI" should {
    val controller = new Controller()
    val gui = new GUI(controller)

    "should have this output when is updated" in {
      val out = new ByteArrayOutputStream();
      Console.withOut(out){
        controller.notifyObservers
      }
      out.toString should include ("still in progress")
    }

    "should have this output when command is processed" in {
      gui.processCommands("187")
      val out = new ByteArrayOutputStream();
      Console.withOut(out){
        controller.notifyObservers
      }
      out.toString should include ("still in progress")
    }
//    "should have this output when run()" in {
//      gui.run().toString should be (println("still in progress"))
//    }
  }
}
