package controller

import model.GameConfig
import util.Command

class CommandCombineCard(controller: Controller, idx1: Int, idx2: Int) extends Command{
  var gameConfig: GameConfig = controller.gameConfig
  var gameState: GameState.Value = controller.gameState

  override def doStep: Unit = controller.combineCard(idx1, idx2)

  override def undoStep: Unit = {
    controller.gameState = gameState
    controller.gameConfig = gameConfig
  }

  override def redoStep: Unit = controller.combineCard(idx1, idx2)
}
