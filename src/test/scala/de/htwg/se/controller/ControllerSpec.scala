//package de.htwg.se.controller
//
//import java.io.ByteArrayOutputStream
//
//import de.htwg.se.de.htwg.se.model.{Card, Deck, GameConfig, Hand, Player}
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//import de.htwg.se.de.htwg.se.util.Observer
//
//class ControllerSpec extends AnyWordSpec with Matchers {
//  "A Controller" when {
//    "new" should {
//      val de.htwg.se.controller = new Controller()
//      val observer = new Observer {
//        var updated: Boolean = false
//        def isUpdated: Boolean = updated
//        override def update: Boolean = {updated = true; updated}
//      }
//      de.htwg.se.controller.add(observer)
//      val deck = new Deck
//
//      "when game is Running" in {
//        de.htwg.se.controller.running = IsRunning()
//
//        val out = new ByteArrayOutputStream();
//        Console.withOut(out) {
//          de.htwg.se.controller.getState()
//        }
//        out.toString should include ("Game is running!")
//      }
//      "when game is not Running" in {
//        de.htwg.se.controller.running = IsNotRunning()
//
//        val out = new ByteArrayOutputStream();
//        Console.withOut(out) {
//          de.htwg.se.controller.getState()
//        }
//        out.toString should include ("Game is not running!")
//      }
//
//      "notify its observer after init game" in {
//        de.htwg.se.controller.performInitGame(2)
//        observer.updated should be(true)
//      }
//      "get active player name" in {
//        de.htwg.se.controller.gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0, Vector[Player]())
//        de.htwg.se.controller.gameConfig = de.htwg.se.controller.gameConfig.createPlayer("SETest")
//        de.htwg.se.controller.getActivePlayerName should be("SETest")
//      }
//      "get player name" in {
//        de.htwg.se.controller.gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 1, Vector[Player]())
//        de.htwg.se.controller.getPlayerName should be("Please enter Playername 2:")
//      }
//      "notify its observer when performing set player name" in {
//        val playerList = Vector[Player](Player("SE1", Hand(Vector[Card]()), Card(0)))
//        de.htwg.se.controller.gameConfig = GameConfig(playerList, deck.resetDeck(), 0, Vector[Player]())
//        de.htwg.se.controller.performSetPlayerName("SETest")
//        observer.updated should be(true)
//        de.htwg.se.controller.gameConfig.getActivePlayerName should be("SETest")
//      }
//    }
//  }
//}