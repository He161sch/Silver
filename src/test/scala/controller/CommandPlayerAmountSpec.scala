package controller

import model.{Card, Deck, GameConfig, Hand, Player}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.UndoManager

class CommandPlayerAmountSpec extends AnyWordSpec with Matchers{
  "PlayerAmountCommand" should {
    val undoManager = new UndoManager
    val controller = new Controller
    val deck = new Deck()
    val tempGameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0, Vector[Player]())

    "doStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandPlayerAmount(controller, 2))
      controller.gameConfig.players.size should be (2)
    }
    "undoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandPlayerAmount(controller, 1))
      undoManager.undoStep()
      controller.gameConfig.players.size should be (0)
    }
    "redoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandPlayerAmount(controller, 1))
      undoManager.redoStep()
      controller.gameConfig.players.size should be (2)
    }
  }
}
