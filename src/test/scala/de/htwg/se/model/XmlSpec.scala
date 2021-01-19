package de.htwg.se.model

import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.fileiocomponent.fileioxmlimpl.Xml
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.PlayerInterface
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.xml.Node

class XmlSpec extends AnyWordSpec with Matchers {
  "A Xml FileIO" when {
    "loading" should {
      val playerone = Player("Marco", Hand(Vector(Card(false, 1))), Card(false, 14))
      val playertwo = Player("Heiko", Hand(Vector(Card(false, 2))), Card(false, 14))
      val controller = new Controller(GameConfig(Vector[PlayerInterface](playerone, playertwo), winners = Vector(playerone)))
      val xml = new Xml()

      "get the correct game- Config/State" in {
        xml.save(controller)
        val loadedController = xml.load


        loadedController.gameState should be (controller.gameState)
        loadedController.gameConfig.getDeck should be(controller.gameConfig.getDeck)
        loadedController.gameConfig.getActivePlayerIdx should be(controller.gameConfig.getActivePlayerIdx)
        xml.toInt("1") should be (Some(1))
        xml.toInt("Test") should be (None)
      }
    }
  }
}
