package controller


import model.{Card, Hand, Player}
import util.Observable

import scala.util.Random



class Controller() extends Observable {
  val r: Random.type = scala.util.Random
  var p1: Player = createPlayer()
  var newCard: Card = Card(0)
  var viewedCard: Card = Card(0)
  var gamestate: State.Value = State.CreatePlayer


  def createPlayer(): Player ={
    Player("Player 1", randomHand())

  }

  def drawCard(): Unit = {
    gamestate = State.DrawCard
    newCard = Card(r.nextInt(14))
    notifyObservers(State.DrawCard)
  }

  def viewCard(idx: Int): Unit ={
    gamestate = State.ViewCard
    viewedCard = p1.hand.cards(idx)
    notifyObservers(State.ViewCard)
  }


  def switchCard(idx: Int): Unit = {
    gamestate = State.SwitchCard
    p1 = Player(p1.name, Hand(p1.hand.cards.patch(idx, List(newCard), 1)))
    notifyObservers(State.SwitchCard)
  }

  def showHandValue(): Unit ={
    gamestate = State.ShowHandValue
    notifyObservers(State.ShowHandValue)
  }

  def combineCard(idx1: Int, idx2: Int): Unit ={
    gamestate = State.CombineCard
    if (idx1 >= p1.hand.cards.size || idx2 >= p1.hand.cards.size){
      println("check number of cards")
      println(p1.toString)
      return
    }
    if(p1.hand.cards(idx1).number.equals(p1.hand.cards(idx2).number)){
      val hand = Hand(p1.hand.cards.updated(idx1, newCard))
      p1 = Player(p1.name, Hand(p1.hand.removeAtIdx(idx2, hand.cards)))
      notifyObservers(State.CombineCard)
    } else {
      printf("card values are not the same! (%d, %d)\n",p1.hand.cards(idx1).number, p1.hand.cards(idx2).number)
      notifyObservers(State.CombineCard)
    }
  }

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

  def handToString: String = Hand.toString()

  def playerToString: String = Player.toString()

  def getCardValue: Int = newCard.number

  def getViewedCard: Int = viewedCard.number

  def ObserverInState(): Unit = {
    notifyObservers(gamestate)
  }
}
