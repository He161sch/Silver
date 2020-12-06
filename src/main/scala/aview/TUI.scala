package aview


import controller.Controller
import controller.GameState._
import util.Observer


class TUI(controller: Controller) extends Observer with UIFactory {

  controller.add(this)

   override def processCommands(input: String): Unit = {
     val inputsplit = input.split(" ").toList
     if (controller.gameState == WelcomeState) {
      input match {
        case "z" => controller.undoStep
        case "y" => controller.redoStep
        case _ => initPlayers(input)
      }
    } else if (controller.gameState == InputName) {
      input match {
        case "z" => controller.undoStep
        case "y" => controller.redoStep
        case _ => controller.performSetPlayerName(input)
      }
    } else if (controller.gameState == DRAWEDCARD) {
      inputsplit.head match {
        case "z" => controller.undoStep
        case "y" => controller.redoStep
        case "s" =>  {
          if(inputsplit(1).matches("1|2|3|4|0")) {
            controller.performSwitchCard(inputsplit(1).toInt)
          }
        }
      }
    } else {
      processInputLine(input)
    }
  }

  def initPlayers(input: String): Unit = {
    if (!List("2", "3").contains(input)) {
      println("You can only play with 2 or 3 Players\n. . .\nTry again")
    } else {
      controller.performInitGame(input.toInt)
    }
  }



  def processInputLine(input: String): Unit = {
    val inputsplit = input.split(" ").toList
    inputsplit.head match{
      case "d" => controller.drawCard()
      case "state" => controller.getState()
      case _ => println("unknown command ... Try again")
//          println("new card is: " + controller.getCardValue)
//        } else if (inputsplit.head.matches("v") && inputsplit(1).matches("1|2|3|4|0")){
//          controller.viewCard(inputsplit(1).toInt)
//          println("viewed card is: " + controller.getViewedCard)
//        } else if (inputsplit.head.matches("s") && inputsplit(1).matches("1|2|3|4|0")){
//          controller.switchCard(inputsplit(1).toInt)
//        } else if (inputsplit.head.matches("e")){  //end
//          controller.showHandValue()
//          System.exit(0)
//        } else if (inputsplit.head.matches("c") && inputsplit(1).matches("1|2|3|4|0") && inputsplit(2).matches("1|2|3|4|0")) {   //combine
//          controller.combineCard(inputsplit(1).toInt, inputsplit(2).toInt)
//        }else{
//          println("ungültiger befehl")
//        }
//    }
    }
  }

  override def update: Boolean = {
    controller.gameState match {
      case WelcomeState => {
        println("Welcome to Silver :)\nHow many players want to play[2 or 3]?")
      }
      case InputName => {
        println(controller.getPlayerName)
      }
      case NEWGAMESTART => {
        println("A new Game started ... Deck is now shuffeled!")
      }
      case PLAYER_TURN => {
        println(controller.getActivePlayerName + "'s turn. Draw or View a Card?(d/ v [0-4])\n")
        println(controller.gameStateToString)
      }
      case DRAWEDCARD => {
        print("Your drawed Card is: ")
        println(controller.printdrawedCard())
        println("Do you want to Swap or Combine the drawn Card?[s [0-4] / c [0-4] [0-4]")
      }
      case SWITCHCARD => {
        println("You switched the draw Card with on of yours")
        println(controller.gameStateToString)
      }
    }
    true
  }

}