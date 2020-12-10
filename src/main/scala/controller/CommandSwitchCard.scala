package controller

import util.Command
import model.GameConfig

class CommandSwitchCard(controller: Controller, idx: Int) extends Command {
  var gameConfig: GameConfig = controller.gameConfig
  var gameState: GameState.Value = controller.gameState

  override def doStep(): Unit = controller.switchCard(idx)

  override def undoStep(): Unit = {
  controller.gameState = gameState
  controller.gameConfig = gameConfig
  }

  override def redoStep(): Unit = controller.switchCard(idx)
}
