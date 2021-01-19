package de.htwg.se.controller.controllercomponent

import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.playerbaseimpl
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import de.htwg.se.util.UndoManager
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CommandViewCardSpec extends AnyWordSpec with Matchers{
  "Switch Command" should {
    val undoManager = new UndoManager
    val controller = new Controller(GameConfig())
    val deck = new Deck()
    val playerHandCards = Vector(Card(false, 0), Card(false, 1), Card(false, 1), Card(false, 5))
    val tempGameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](playerbaseimpl.Player("SE1", Hand(playerHandCards), Card(false, 0))),
      deck.resetDeck(), Card(false, 14),0, Vector[Player]())

    "doStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandViewCard(controller, 0))
      controller.gameConfig.getPlayerAtIdx(0).getHand.getAllCards.size should be (4)
    }
    "undoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandViewCard(controller,0))
      undoManager.undoStep()
      controller.gameConfig.getPlayerAtIdx(0).getHand.getAllCards.size should be (4)
    }
    "redoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandViewCard(controller,0))
      undoManager.redoStep()
      controller.gameConfig.getPlayerAtIdx(0).getHand.getAllCards.size should be (4)
    }
  }
}
