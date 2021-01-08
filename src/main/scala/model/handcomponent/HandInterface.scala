package model.handcomponent

import model.cardcomponent.cardbaseimlp.Card

trait HandInterface {

  def toString: String

  def handValue(): Int

  def removeAtIdx[T](idx: Int, vectorToRemoveFrom: Vector[Card]): Vector[Card]
}
