package aview

import controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class UIFactorySpec extends AnyWordSpec with Matchers{
  "A UserInterface" should {
    val userInterface = new UIFactory {
      var stubbedInput = ""

      override def processCommands(input: String) = {
        stubbedInput = input
      }
    }

    "process command" in {
      userInterface.processCommands("SETest")
      userInterface.stubbedInput should equal("SETest")
    }
  }

  "The Text UI" when {
    "is created" should {
      val controller = new Controller()
      val uiType = "tui"
      val ui = UIFactory(uiType, controller)
    }
  }
//      "have instance of TUI" in {
//        ui.isInstanceOf[TUI] should be(true)
//      }
//    }
//  }

  "The Graphical UI" when {
    "is created" should {
      val controller = new Controller()
      val uiType = "GUI"
      val ui = UIFactory(uiType, controller)

//    "have instance of GUI" in {
//      ui.isInstanceOf[GUI] should be(true)
//    }
    }
  }
}
