package controller.controllercomponent

import controller.controllercomponent.controllerbaseimpl.Controller
import model.cardcomponent.cardbaseimlp.Card
import model.deckcomponent.deckbaseimpl.Deck
import model.gameconfigcomponent.gameconfigbaseimpl
import model.handcomponent.handbaseimpl.Hand
import model.playercomponent.playerbaseimpl
import model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.UndoManager

class CommandInputNamesSpec extends AnyWordSpec with Matchers{
  "NameCommand" should {
    val undoManager = new UndoManager
    val controller = new Controller
    val deck = new Deck()
    val playerHandCards = Vector(Card(0), Card(1), Card(1), Card(5))
    val tempGameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](playerbaseimpl.Player("SE1", Hand(playerHandCards), Card(0))),
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
