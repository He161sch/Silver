package de.htwg.se.model.fileiocomponent.fileioxmlimpl

import de.htwg.se.controller.controllercomponent.{ControllerInterface, GameState}
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller

import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.fileiocomponent.FileIOInterface
import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import de.htwg.se.model.handcomponent.HandInterface
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.PlayerInterface
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.deckcomponent.DeckInterface
import scala.xml.{Elem, Node, NodeSeq, PrettyPrinter}

class Xml extends FileIOInterface {

  def getGameConfigXml(seq: NodeSeq): GameConfigInterface = {
    GameConfig(
      playersFromXml(seq \ "players"),
      Deck(cardsFromXml(seq \ "deck")),
      cardFromXml(seq \ "discardDeck"),
      (seq \ "activePlayerIdx").text.toInt,
      playersFromXml(seq \\ "winner")
    )
  }

  def getControllerXml(xml: Elem): ControllerInterface = {
    val controller = new Controller(getGameConfigXml(xml \ "gameConfig"))
    controller.gameState = GameState.withName((xml \ "gameState").text)
    controller
  }


  override def load: ControllerInterface = {
    val file = scala.xml.XML.loadFile("SilverGameSaved.xml")

    getControllerXml(file)
  }

  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val printWriter = new PrintWriter(new File("SilverGameSaved.xml"))
    val prettyPrint = new PrettyPrinter(120,4)
    val xml = prettyPrint.format(controllerToXml(controller))
    printWriter.write(xml)
    printWriter.close
  }


  def playersFromXml(seq: NodeSeq): Vector[PlayerInterface] = {
    var players = Vector[PlayerInterface]()
    val playerSeq = seq \\ "player"
    for (p <- playerSeq) {
      players = players :+ Player(
        (p \ "name").text,
        Hand(cardsFromXml(p \ "hand")),
        cardFromXml(p \ "newCard")
      )
    }
    players
  }

  def playerFromXml(seq: NodeSeq): PlayerInterface = {
    val cards = cardsFromXml(seq \ "hand" \\ "card")
    Player(
      (seq \ "name").text,
      Hand(cards), Card(false, 14)
    )
  }

  def cardFromXml(seq: NodeSeq): CardInterface = {
    var cards = Card(false, 0)

    for (c <- seq) {
      cards = Card(false, c.text.toInt)
    }

    cards
  }

  def cardsFromXml(seq: NodeSeq): Vector[CardInterface] = {
    var cards = Vector[CardInterface]()
    val cardSeq = seq \\ "card"

    for(c <- cardSeq){
      cards = cards :+ Card(false, c.text.toInt)
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

  def controllerToXml(controller: ControllerInterface): Node = {
    var c: Node =
      <controller>
        <gameState>{controller.gameState}</gameState>
      </controller>
    c = addNode(c, gameConfigToXml(controller.gameConfig))
    c
  }

  def gameConfigToXml(gameConfig: GameConfigInterface): Node = {
    var c: Node =
      <gameConfig>
        <activePlayerIdx>{gameConfig.getActivePlayerIdx}</activePlayerIdx>
      </gameConfig>
    c = addNode(c, playersToXml(gameConfig.getAllPlayers))
    c = addNode(c, deckToXml(gameConfig.getDeck))
    c = addNode(c, winnersToXml(gameConfig.getWinners))
    c
  }

  def winnersToXml(players: Vector[PlayerInterface]): Node = {
    var winnersNode: Node = <winners></winners>

    for (p <- players) {
      val player = playerToXml(p)
      winnersNode = addNode(winnersNode, player)
    }
    winnersNode

  }

  def deckToXml(deck: DeckInterface): Node = {
    var deckNode: Node = <deck></deck>
    for (c <- deck.getAllCards) {
      val card = cardToXml(c)
      deckNode = addNode(deckNode, card)

    }
    deckNode

  }


  def playersToXml(players: Vector[PlayerInterface]): Node = {
    var playersNode: Node = <players></players>
    for (p <- players) {
      val player = playerToXml(p)
      playersNode = addNode(playersNode, player)
    }
    playersNode
  }

  def playerToXml(player: PlayerInterface): Node = {
    var c: Node =
      <player>
        <name>{player.getName}</name>
      </player>

    c = addNode(c, handToXml(player.getHand))
    c
  }

  def handToXml(hand: HandInterface): Node = {
    var handN: Node = <hand></hand>
    for (c <- hand.getAllCards) {
      val card = cardToXml(c)
      handN = addNode(handN, card)
    }
    handN
  }

  def cardsToXml(card: CardInterface): Node = {
    <card>{card.getNumber}</card>
  }


  def cardToXml(cards: CardInterface): Elem = {
    <card>
      <number>{cards.getNumber}</number>
    </card>

  }



  def addNode(to: Node, newNode: Node) = to match {
    case Elem(prefix, label, attributes, scope, child@_*) => Elem(prefix, label, attributes, scope, child ++ newNode: _*)
    case _ => println("could not find node"); to
  }
}
