package controller
import controller.Controller

class Context {

  var state:State = _
  state = new WelcomeState()

  def handle(): Unit = {
    state.handle()
  }
}

trait State {
  def handle()
}

class WelcomeState() extends State {
  override def handle(): Unit = {
    println("WELCOME TO SILVER")
  }
}

class DrawCard() extends State {
  override def handle(): Unit = {
    println("you drew a Card")
  }
}

class ViewCard() extends State {
  override def handle(): Unit = {
    println("You viewed a card")
  }
}

class SwitchCard() extends State {
  override def handle(): Unit = {
    println("You switched a Card")
  }
}

class CombineCard() extends State {
  override def handle(): Unit = {
    println("You combined two Cards")
  }
}
