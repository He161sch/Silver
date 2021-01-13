package de.htwg.se.aview

import de.htwg.se.controller.controllercomponent.GameState._
import de.htwg.se.controller.controllercomponent.{ControllerInterface, updateData, Saved}
import de.htwg.se.util.Observer

import scala.io.StdIn.readLine
import scala.swing._



class TUI(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  def run(): Unit = {
    controller.gameState = WelcomeState
    controller.publish(new updateData)
    var input: String = ""

    while (input != "q" && controller.gameState != EndGame) {
      input = readLine()
      processCommands(input)
    }
  }

  def processCommands(input: String): Unit = {

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
        case "s" => validateSwitch(inputsplit(1).toInt).getOrElse(println(inputsplit(1).toInt + " is out of Bounds try again"))
        case "c" =>
          if (inputsplit(1).matches("1|2|3|4|0") && inputsplit(2).matches("1|2|3|4|0")) {
            controller.performCombineCard(inputsplit(1).toInt, inputsplit(2).toInt)
          }
        case _ => println("Ungültiger befehl")
      }
    } else if (controller.gameState == VIEWCARD) {
      inputsplit.head match {
        case "z" => controller.undoStep
        case "y" => controller.redoStep
        case "v" =>
          if (inputsplit(1).matches("1|2|3|4|0")) {
            controller.viewCard(inputsplit(1).toInt)
            controller.nextPlayer()
          }
        case _ => println("Ungültiger befehl")
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
      case "v" => controller.viewCard()
      case "state" => controller.getState()
      case "cabo" => controller.whoWon()
      case "save" => controller.save
      case "load" => controller.load
      case "new" => controller.newGame
      case _ => println("unknown command ... Try again")

    }
  }

  reactions += {
    case _ => update
    case event: Saved => print("Game saved :)\n")
  }

  def update: Unit = {
    controller.gameState match {
      case WelcomeState => {
        println("Welcome to Silver :)\nHow many players want to play[2 or 3]?")
      }
      case InputName => {
        println(controller.getPlayerName)
      }
      case NEWGAME => {
        println("A new Game started ... Deck is now shuffeled!")
      }
      case PLAYER_TURN => {
        print(controller.gameConfig.getActivePlayerName + "'s turn. Draw or View a Card?(d/v)\n")
        println(controller.gameStateToString)
      }
      case DRAWEDCARD => {
        print("Your drawed Card is: ")
        println(controller.printdrawedCard())
        println("Do you want to Swap or Combine the drawn Card?[s [0-4] / c [0-4] [0-4]")
      }
      case SWITCHCARD => {
        println("You switched the drawn Card with on of yours")
        println(controller.gameStateToString)
      }
      case COMBINECARD => {
        println(controller.gameStateToString)
      }
      case VIEWCARD => {
        println(controller.gameStateToString)
        println("Which Card you want to view ?[v [0-4]]")
      }
      case PlayerWon => {
        println(controller.gameStateToString)
      }
      case EndGame => {
        println("Was fun playing!\n\n")
      }
      case FALSECOMMAND => print("Command isn't allowed\n")
    }
  }

  def validateSwitch(idx: Int): Option[Unit] = {
    if (idx >= 0 && idx < 5) {
      Some(controller.performSwitchCard(idx))
    } else {
      None
    }

  }
}