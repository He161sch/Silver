package de.htwg.se.model.handcomponent

import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card

trait HandInterface {

  def toString: String
  def handValue(): Int
  def getCard(idx: Int): CardInterface
  def getAllCards: Vector[CardInterface]
  def removeAtIdx[T](idx: Int, vectorToRemoveFrom: Vector[CardInterface]): Vector[CardInterface]
  def setAllCardsFalse: Unit
}
