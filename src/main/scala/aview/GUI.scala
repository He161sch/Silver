package aview

import controller.{Controller, State}
import util.Observer

class GUI(controller: Controller) extends Observer with UIInterface{
  controller.add(this)

  override def update(status: State.Value): Boolean = {
    println("Try it with TUI again");true
  }

  override def inputCommand(input: String): Unit = {
    println("Try it with TUI again")
  }
}
