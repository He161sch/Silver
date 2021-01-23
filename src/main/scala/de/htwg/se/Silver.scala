package de.htwg.se

import com.google.inject.{Guice, Injector}
import de.htwg.se.aview.{TUI, UIFactory}
import de.htwg.se.aview.gui.WelcomePanel
import de.htwg.se.controller.controllercomponent.ControllerInterface

import scala.util.{Failure, Success, Try}

object Silver {
  val injector: Injector = Guice.createInjector(new SilverModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])


  val uiType: String = if (System.getenv("UI_TYPE") == null) "both" else System.getenv("UI_TYPE")

  def main(args: Array[String]): Unit = {

    Try(UIFactory(uiType, controller)) match {
      case Failure(v) => println("Could not start UI because: " + v.getMessage + v.printStackTrace())
      case Success(v) => println("GOOD BYE")
    }
  }
}

