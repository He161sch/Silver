package aview

import controller.{Controller, State}
import util.Observer

class GUI(controller: Controller) extends Observer with UIInterface{
  controller.add(this)

  override def update(status: State.Value): Boolean = {
    println("GUI is still in progress");true
  }

  override def inputCommand(input: String): Unit = {
    println("GUI is still in progress")
    println("Try it with TUI again")
    System.exit(0)
  }
}
