package de.htwg.se.controller.controllercomponent

import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.util.UndoManager

class CommandPlayerAmountSpec extends AnyWordSpec with Matchers{
  "PlayerAmountCommand" should {
    val undoManager = new UndoManager
    val controller = new Controller(GameConfig())
    val deck = new Deck()
    val tempGameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](), deck.resetDeck(), Card(false, 14),0, Vector[Player]())

    "doStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandPlayerAmount(controller, 2))
      controller.gameConfig.getAllPlayers.size should be (2)
    }
    "undoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandPlayerAmount(controller, 1))
      undoManager.undoStep()
      controller.gameConfig.getAllPlayers.size should be (0)
    }
    "redoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandPlayerAmount(controller, 1))
      undoManager.redoStep()
      controller.gameConfig.getAllPlayers.size should be (2)
    }
  }
}
