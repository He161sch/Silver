package controller



import com.sun.jdi.Value
import controller.GameState.CreatePlayer
import model.{Card, Hand, Player}
import util.Observable

import scala.util.Random

object GameState extends Enumeration {
  type GameState = Value
  val NewGame, CreatePlayer, ViewCard, SwitchCard, ShowHandValue, CombineCard, DrawCard, CCFailed, OOB = Value
}

class Controller() extends Observable {
  val r: Random.type = scala.util.Random
  var p1: Player = createPlayer()
  var newCard: Card = Card(0)
  var viewedCard: Card = Card(0)
  var gamestate: GameState.Value = CreatePlayer

  import GameState._

  def createPlayer(): Player ={
    Player("Player 1", randomHand())

  }

  def newGame(): Unit ={
    gamestate = NewGame
    p1 = Player("Player 1", randomHand())
    notifyObservers

  }
  def drawCard(): Unit = {
    gamestate = DrawCard
    newCard = Card(r.nextInt(14))
    notifyObservers
  }

  def viewCard(idx: Int): Unit ={
    gamestate = ViewCard
    viewedCard = p1.hand.cards(idx)
    notifyObservers
  }

  def getCardValue: Int = newCard.number

  def getViewedCard: Int = viewedCard.number


  def switchCard(idx: Int): Unit = {
    gamestate = SwitchCard
    p1 = Player(p1.name, Hand(p1.hand.cards.patch(idx, List(newCard), 1)))
    notifyObservers
  }


  def showHandValue(): Unit ={
    gamestate = ShowHandValue
    notifyObservers
  }

  def getHandValue: Int = p1.hand.handValue()


  def combineCard(idx1: Int, idx2: Int): Unit ={
    if (idx1 >= p1.hand.cards.size || idx2 >= p1.hand.cards.size){
      gamestate = OOB
      notifyObservers
    } else
      if(p1.hand.cards(idx1).number.equals(p1.hand.cards(idx2).number)){
      gamestate = CombineCard
      val hand = Hand(p1.hand.cards.updated(idx1, newCard))
      p1 = Player(p1.name, Hand(p1.hand.removeAtIdx(idx2, hand.cards)))
      notifyObservers
    } else {
      gamestate = CCFailed
      notifyObservers
    }
  }


  def handToString: String = Hand.toString()


  def playerToString: String = Player.toString()


  def randomHand(): Hand ={
    val r = scala.util.Random
    var cards = List[Card]()
    for(x <- 0 to 4){
      cards = Card(r.nextInt(14)) :: cards
    }
    val hand = Hand(cards)
    hand
  }

  def statusToString: String = {
    gamestate match {
      case _ => p1.toString
    }
  }

}
