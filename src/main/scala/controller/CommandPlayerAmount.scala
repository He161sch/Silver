package controller

import model.GameConfig
import util.Command

class CommandPlayerAmount(controller: Controller, playerAmount: Int) extends Command {
  var gameConfig: GameConfig = controller.gameConfig
  var gameState: GameState.Value = controller.gameState

  override def doStep: Unit = controller.initGame(playerAmount)

  override def undoStep: Unit = {
    controller.gameState = gameState
    controller.gameConfig = gameConfig
  }

  override def redoStep: Unit = controller.initGame(playerAmount)
}
