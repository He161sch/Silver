package de.htwg.se.controller.controllercomponent

trait State {
  def handle(e: State): (State, String)
}

case class IsRunning() extends State{
  override def handle(e: State): (State, String) = {
    e match {
      case isRunning: IsRunning => (IsRunning(), "Game is running!")
      case isNotRunning: IsNotRunning => (IsNotRunning(), "Game is not running!")
    }
  }
}

case class IsNotRunning() extends State{
  override def handle(e: State): (State, String) = {
    e match {
      case isRunning: IsRunning => (IsRunning(), "Game is running!")
      case isNotRunning: IsNotRunning => (IsNotRunning(), "Game is not running!")
    }
  }
}
