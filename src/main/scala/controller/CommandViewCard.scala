package controller

import model.GameConfig
import util.Command

class CommandViewCard(controller: Controller, idx: Int) extends Command{
  var gameConfig: GameConfig = controller.gameConfig
  var gameState: GameState.Value = controller.gameState

  override def doStep(): Unit = controller.viewCard(idx)

  override def undoStep(): Unit = {
    controller.gameState = gameState
    controller.gameConfig = gameConfig
  }

  override def redoStep(): Unit = controller.viewCard(idx)
}
