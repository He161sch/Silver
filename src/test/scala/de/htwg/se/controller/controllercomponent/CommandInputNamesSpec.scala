package de.htwg.se.controller.controllercomponent

import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.playerbaseimpl
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.util.UndoManager

class CommandInputNamesSpec extends AnyWordSpec with Matchers{
  "NameCommand" should {
    val undoManager = new UndoManager
    val controller = new Controller(GameConfig())
    val deck = new Deck()
    val playerHandCards = Vector(Card(false, 0), Card(false, 1), Card(false, 1), Card(false, 5))
    val tempGameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](playerbaseimpl.Player("SE1", Hand(playerHandCards), Card(false, 0))),
      deck.resetDeck(), Card(false, 14), 0, Vector[Player]())

    "doStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandInputNames(controller, "SETest"))
      controller.gameConfig.getPlayerAtIdx(0).getName should be ("SETest")
    }
    "undoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandInputNames(controller, "SETest"))
      undoManager.undoStep()
      controller.gameConfig.getPlayerAtIdx(0).getName should be("SE1")
    }
    "redoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandInputNames(controller, "SETest"))
      undoManager.redoStep()
      controller.gameConfig.getPlayerAtIdx(0).getName should be ("SETest")
    }
  }
}
