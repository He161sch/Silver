package model.handcomponent.handbaseimpl

import model.cardcomponent.cardbaseimlp.Card
import model.handcomponent.HandInterface

import com.google.inject.Inject

case class Hand (cards: Vector[Card]) extends HandInterface{

  override def toString: String = "[" + cards.mkString(", ") + "]\n"

  def handValue(): Int = {
    var sum: Int = 0
    for (card <- cards) {
      sum += card.number
    }
    sum
  }

  def removeAtIdx[T](idx: Int, vectorToRemoveFrom: Vector[Card]): Vector[Card] = {
    val x = vectorToRemoveFrom.drop(idx + 1)
    val y = vectorToRemoveFrom.dropRight(vectorToRemoveFrom.size - idx)
    val newVector = y ++ x
    newVector
  }


}
