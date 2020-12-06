package aview

import controller.Controller
import util.Observer

class GUI(controller: Controller) extends Observer with UIFactory{
  controller.add(this)

  override def processCommands(input: String): Unit = {
    println("gui not implemented yet")
  }

  override def update: Boolean = {
    println("gui not implemented yet")
    true
  }
}
