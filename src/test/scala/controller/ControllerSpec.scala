//package controller
//
//import java.io.ByteArrayOutputStream
//
//import model.{Card, Deck, GameConfig, Hand, Player}
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//import util.Observer
//
//class ControllerSpec extends AnyWordSpec with Matchers {
//  "A Controller" when {
//    "new" should {
//      val controller = new Controller()
//      val observer = new Observer {
//        var updated: Boolean = false
//        def isUpdated: Boolean = updated
//        override def update: Boolean = {updated = true; updated}
//      }
//      controller.add(observer)
//      val deck = new Deck
//
//      "when game is Running" in {
//        controller.running = IsRunning()
//
//        val out = new ByteArrayOutputStream();
//        Console.withOut(out) {
//          controller.getState()
//        }
//        out.toString should include ("Game is running!")
//      }
//      "when game is not Running" in {
//        controller.running = IsNotRunning()
//
//        val out = new ByteArrayOutputStream();
//        Console.withOut(out) {
//          controller.getState()
//        }
//        out.toString should include ("Game is not running!")
//      }
//
//      "notify its observer after init game" in {
//        controller.performInitGame(2)
//        observer.updated should be(true)
//      }
//      "get active player name" in {
//        controller.gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 0, Vector[Player]())
//        controller.gameConfig = controller.gameConfig.createPlayer("SETest")
//        controller.getActivePlayerName should be("SETest")
//      }
//      "get player name" in {
//        controller.gameConfig = GameConfig(Vector[Player](), deck.resetDeck(), 1, Vector[Player]())
//        controller.getPlayerName should be("Please enter Playername 2:")
//      }
//      "notify its observer when performing set player name" in {
//        val playerList = Vector[Player](Player("SE1", Hand(Vector[Card]()), Card(0)))
//        controller.gameConfig = GameConfig(playerList, deck.resetDeck(), 0, Vector[Player]())
//        controller.performSetPlayerName("SETest")
//        observer.updated should be(true)
//        controller.gameConfig.getActivePlayerName should be("SETest")
//      }
//    }
//  }
//}