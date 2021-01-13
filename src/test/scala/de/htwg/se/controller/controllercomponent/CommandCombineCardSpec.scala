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

class CommandCombineCardSpec extends AnyWordSpec with Matchers{
  "NameCommand" should {
    val undoManager = new UndoManager
    val controller = new Controller(GameConfig())
    val deck = new Deck()
    val playerHandCards = Vector(Card(false, 0), Card(false, 1), Card(false, 1), Card(false, 5))
    val tempGameConfig = gameconfigbaseimpl.GameConfig(Vector[Player](playerbaseimpl.Player("SE1", Hand(playerHandCards), Card(false, 0))),
      deck.resetDeck(), Card(false, 14),0, Vector[Player]())

    "doStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandCombineCard(controller,0, 1))
      controller.gameConfig.getPlayerAtIdx(0).getHand.getAllCards.size should be (4)
    }
    "undoStep" in {
      controller.gameConfig = tempGameConfig
      undoManager.doStep(new CommandCombineCard(controller,0, 1))
      undoManager.undoStep()
      controller.gameConfig.getPlayerAtIdx(0).getHand.getAllCards.size should be (4)
    }
//    "redoStep" in {
//      de.htwg.se.controller.gameConfig = tempGameConfig
//      undoManager.doStep(new CommandCombineCard(de.htwg.se.controller,0, 1))
//      undoManager.redoStep()
//      de.htwg.se.controller.gameConfig.players(0).hand.cards.size should be (4)
//    }
  }
}
