package de.htwg.se.model

import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.fileiocomponent.fileiojasonimpl.Json
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.PlayerInterface
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class JsonSpec extends AnyWordSpec with Matchers {

  "A Json FileIO" when {
    "loading" should {
      val playerone = Player("Marco", Hand(Vector(Card(false, 1))), Card(false, 15))
      val playertwo = Player("Heiko", Hand(Vector(Card(false, 1))), Card(false, 15))
      val controller = new Controller(GameConfig(Vector[PlayerInterface](playerone, playertwo)))
      val json = new Json()

      "get the correct game- Config/State" in {
        json.save(controller)
        val loadedController = json.load

        loadedController.gameState should be (controller.gameState)
        loadedController.gameConfig.getDeck should be(controller.gameConfig.getDeck)
        loadedController.gameConfig.getActivePlayerIdx should be(controller.gameConfig.getActivePlayerIdx)
        loadedController.gameConfig.getWinners should be(controller.gameConfig.getWinners)
        json.toInt("1") should be (Some(1))
        json.toInt("Test") should be (None)
      }
    }
  }
}
