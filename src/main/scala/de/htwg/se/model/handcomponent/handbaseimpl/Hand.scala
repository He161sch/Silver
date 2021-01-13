package de.htwg.se.model.handcomponent.handbaseimpl

import de.htwg.se.model.handcomponent.HandInterface
import de.htwg.se.model.cardcomponent.CardInterface

case class Hand (cards: Vector[CardInterface]) extends HandInterface{

  override def toString: String = "[" + cards.mkString(", ") + "]\n"

  def handValue(): Int = {
    var sum: Int = 0
    for (card <- cards) {
      sum += card.getNumber
    }
    sum
  }

  def removeAtIdx[T](idx: Int, vectorToRemoveFrom: Vector[CardInterface]): Vector[CardInterface] = {
    val x = vectorToRemoveFrom.drop(idx + 1)
    val y = vectorToRemoveFrom.dropRight(vectorToRemoveFrom.size - idx)
    val newVector = y ++ x
    newVector
  }

  def getCard(idx: Int): CardInterface = cards(idx)

  def getAllCards: Vector[CardInterface] = cards

  def setAllCardsFalse: Unit = {
    if (getAllCards.size == 5) {
      getCard(0).setVisibility(false)
      getCard(1).setVisibility(false)
      getCard(2).setVisibility(false)
      getCard(3).setVisibility(false)
      getCard(4).setVisibility(false)
    } else if(getAllCards.size == 4) {
      getCard(0).setVisibility(false)
      getCard(1).setVisibility(false)
      getCard(2).setVisibility(false)
      getCard(3).setVisibility(false)
    } else if(getAllCards.size == 3) {
      getCard(0).setVisibility(false)
      getCard(1).setVisibility(false)
      getCard(2).setVisibility(false)
    } else if(getAllCards.size == 2) {
      getCard(0).setVisibility(false)
      getCard(1).setVisibility(false)
    } else {
      getCard(0).setVisibility(false)
    }

  }
}
