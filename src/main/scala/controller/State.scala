package controller

object State extends Enumeration {
  val  CreatePlayer, ViewCard, SwitchCard, ShowHandValue, CombineCard, DrawCard, WelcomeState = Value
}

// roundState, finishedState