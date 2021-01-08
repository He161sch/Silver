package controller.controllercomponent

import controllerbaseimpl.Controller
import model.GameConfig
import util.Command

class CommandInputNames(controller: Controller, playerName: String) extends Command {
  var gameConfig: GameConfig = controller.gameConfig
  var gameState: GameState.Value = controller.gameState

  override def doStep(): Unit = controller.setPlayerName(playerName)

  override def undoStep(): Unit = {
    controller.gameState = gameState
    controller.gameConfig = gameConfig
  }

  override def redoStep(): Unit = controller.setPlayerName(playerName)
}
