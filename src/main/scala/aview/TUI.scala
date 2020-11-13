package aview


import controller.Controller
import util.Observer

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

  override def update: Unit = controller.playerToString
}