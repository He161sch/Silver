package de.htwg.se.model.fileiocomponent.fileiojasonimpl

import de.htwg.se.controller.controllercomponent.{ControllerInterface, GameState}
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.fileiocomponent.FileIOInterface
import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.HandInterface
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.PlayerInterface
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import play.api.libs.json.{JsArray, JsLookupResult, JsNumber, JsObject, JsValue, Json}

import scala.io.Source

class Json extends FileIOInterface{


  def getGameConfigJson(game: JsLookupResult): GameConfigInterface = {
    GameConfig (
      playersFromJson(game \ "players" \\ "player"),
      Deck(cardsFromJson(game \ "deck" \\ "card")),
      cardFromJson(game \ "discardDeck" \\ "card"),
      (game \ "activePlayerIdx").as[Int],
      playersFromJson(game \\ "winner")
    )
  }

  def getControllerJson(json: JsValue): ControllerInterface = {

    val controller = new Controller(getGameConfigJson(json \ "controller" \ "gameConfig"))
    controller.gameState = GameState.withName((json \ "controller" \ "gameState").as[String])
    controller
  }


  override def load: ControllerInterface = {
    val source: String = Source.fromFile("SilverGame.json").getLines().mkString
    val json: JsValue = Json.parse(source)
    getControllerJson(json)
  }

  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val printWriter = new PrintWriter(new File("SilverGame.json"))
    printWriter.write(Json.prettyPrint(controllerToJson(controller)))
    printWriter.close()
  }


  def playersFromJson(seq: Seq[JsValue]): Vector[PlayerInterface] = {
    var players = Vector[PlayerInterface]()
    for (p <- seq) {
      players = players :+ playerFromJson(p)
    }
    players
  }

  def playerFromJson(seq: JsValue): PlayerInterface = {
    val cards = cardsFromJson(seq \ "hand" \\ "card")
    Player(
      (seq \ "name").as[String],
      Hand(cards), Card(false, 14)
    )
  }

  def cardFromJson(seq: Seq[JsValue]): CardInterface = {
    var cards = Card(false, 0)

    for (c <- seq) {
      cards = Card(false, c.as[String].toInt)
    }

    cards
  }

  def cardsFromJson(seq: Seq[JsValue]): Vector[CardInterface] = {
    var cards = Vector[CardInterface]()

    for(c <- seq){
      cards = cards :+ Card(false, c.as[String].toInt)

    }
    cards
  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

  def controllerToJson(controller: ControllerInterface): JsObject = {
    Json.obj(
      "controller" -> Json.obj(
        "gameState" -> controller.gameState.toString,
        "gameConfig" -> gameConfigToJson(controller.gameConfig)
      )
    )
  }

  def gameConfigToJson(gameConfig: GameConfigInterface): JsObject = {
    Json.obj(
      "players" -> playersToJson(gameConfig.getAllPlayers),
      "deck" -> cardsToJson(gameConfig.getDeck.getAllCards),
      "discardDeck" -> cardToJson(gameConfig.getDiscardDeck),
      "activePlayerIdx" -> JsNumber(gameConfig.getActivePlayerIdx),
      "winners" -> playersToJson(gameConfig.getWinners)
    )
  }

  def playersToJson(players: Vector[PlayerInterface]): JsArray = {
    var jSonObject = Json.arr()
    for (p <- players) {
      jSonObject = jSonObject :+ Json.obj(
        "player" -> Json.toJson(playerToJson(p)))
    }
    jSonObject
  }

  def playerToJson(interface: PlayerInterface): JsObject = Json.obj(
    "name" -> interface.getName,
    "hand" -> handToJson(interface.getHand)
  )

  def handToJson(hand: HandInterface): JsArray = {
    cardsToJson(hand.getAllCards)
  }

  def cardsToJson(cards: Vector[CardInterface]): JsArray = {
    var jSonObject = Json.arr()
    for (c <- cards) {
      jSonObject = jSonObject :+ Json.obj(
        "card" -> c.toString
      )
    }
    jSonObject
  }


  def cardToJson(cards: CardInterface): JsArray = {
    var jSonObject = Json.arr()
    jSonObject = jSonObject :+ Json.obj(
      "card" -> cards.toString
    )
    jSonObject
  }
}
