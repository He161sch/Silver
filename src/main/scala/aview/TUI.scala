package aview


import controller.Controller
import util.Observer
import controller.GameState._

class TUI(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String): Unit = {
    val inputsplit = input.split(" ").toList
    inputsplit match{
      case _ =>
        if (inputsplit.head.matches("d")){
          controller.drawCard()
        } else if (inputsplit.head.matches("v") && inputsplit(1).matches("1|2|3|4|0")){
          controller.viewCard(inputsplit(1).toInt)
        } else if (inputsplit.head.matches("s") && inputsplit(1).matches("1|2|3|4|0")){
          controller.switchCard(inputsplit(1).toInt)
        } else if (inputsplit.head.matches("e")){  //end
          controller.showHandValue()
          System.exit(0)
        } else if (inputsplit.head.matches("c") && inputsplit(1).matches("1|2|3|4|0") && inputsplit(2).matches("1|2|3|4|0")) {   //combine
          controller.combineCard(inputsplit(1).toInt, inputsplit(2).toInt)
        }else{
          println("ungültiger befehl")
        }

    }
  }

  override def update: Unit = {
    controller.gamestate match {
      case CreatePlayer => {
        println("Welcome to Silver")
        println(controller.statusToString)
      }
      case DrawCard => {
        println("You drew a Card")
        println("The new Card is: " + controller.getCardValue)
        println(controller.statusToString)
      }
      case ViewCard => {
        println("You viewed a Card")
        println("Cards Value: " + controller.getViewedCard)
        println(controller.statusToString)
      }
      case SwitchCard => {
        println("You switched a Card")
        println(controller.statusToString)
      }
      case ShowHandValue => {
        println("Your HandValue is: " + controller.showHandValue())
        println(controller.statusToString)
      }
      case CombineCard => {
        println("You combined two Cards")
        println(controller.statusToString)
      }
    }
  }
}