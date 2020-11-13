package controller

import model.{Card, Hand, Player}
import util.Observable

import scala.util.Random
import scala.util.control.Breaks.break


class Controller() extends Observable {
  val r: Random.type = scala.util.Random
  var p1: Player = createPlayer()
  var newCard: Card = Card(0)


  def createPlayer(): Player ={
    Player("Player 1", randomHand())
  }


  def viewCard(idx: Int): Unit ={
    printf("Card Value: %d\n", p1.hand.cards(idx).number)
    println(p1.toString)
  }


  def switchCard(idx: Int): Unit = {
    p1 = Player(p1.name, Hand(p1.hand.cards.patch(idx, List(newCard), 1)))
    println(p1.toString)
    notifyObservers
  }


  def showHandValue(): Unit ={
    println(p1.toString)
    println(p1.hand.handValue())
  }


  def combineCard(idx1: Int, idx2: Int): Unit ={
    if (idx1 >= p1.hand.cards.size || idx2 >= p1.hand.cards.size){
      println("check number of cards")
      println(p1.toString)
      return
    }
    if(p1.hand.cards(idx1).number.equals(p1.hand.cards(idx2).number)){
      val hand = Hand(p1.hand.cards.updated(idx1, newCard))
      p1 = Player(p1.name, Hand(p1.hand.removeAtIdx(idx2, hand.cards)))
      println(p1.toString)
      notifyObservers
    } else {
      printf("card values are not the same! (%d, %d)\n",p1.hand.cards(idx1).number, p1.hand.cards(idx2).number)
      println(p1.toString)
      notifyObservers
    }
  }

  def drawCard(): Unit = {
    newCard = Card(r.nextInt(14))
    printf("new Card = %d\n", newCard.number)
    println(p1.toString)
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
}
