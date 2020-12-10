package aview

import controller.Controller
import util.Observer

class GUI(controller: Controller) extends Observer with UIFactory{
  controller.add(this)

  override def processCommands(input: String): Unit = {
    println("still in progress")
  }
  def run(): Unit = {
    println("still in progress")
  }

  override def update: Boolean = {
    println("still in progress")
    true
  }
}
