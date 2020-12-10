package controller

import model.{Card, Deck, GameConfig, Hand, Player}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.UndoManager

class CommandInputNamesSpec extends AnyWordSpec with Matchers{
  "NameCommand" should {
    val undoManager = new UndoManager
    val controller = new Controller
    val deck = new Deck()
    val playerHandCards = Vector(Card(0), Card(1), Card(1), Card(5))
    val tempGameConfig = GameConfig(Vector[Player](Player("SE1", Hand(playerHandCards), Card(0))),
      deck.resetDeck(), 0, Vector[Player]())

    "doStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandInputNames(controller, "SETest"))
      controller.gameConfig.players(0).name should be ("SETest")
    }
    "undoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandInputNames(controller, "SETest"))
      undoManager.undoStep()
      controller.gameConfig.players(0).name should be("SE1")
    }
    "redoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandInputNames(controller, "SETest"))
      undoManager.redoStep()
      controller.gameConfig.players(0).name should be ("SETest")
    }
  }
}
