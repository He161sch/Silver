package de.htwg.se.model.deckcomponent.deckbaseimpl

import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.deckcomponent.DeckInterface

import de.htwg.se.model.cardcomponent.CardInterface

import scala.util.Random

case class Deck (cards: Vector[CardInterface] = Vector[CardInterface]()) extends DeckInterface {
  def this() = this(Vector[CardInterface]())

  def drawCards(num: Int): (Deck, Vector[CardInterface]) = {
    var drawedCards = Vector[CardInterface]()

    val from = cards.size - num
    val bis = cards.size - 1

    for (i <- (from to bis)) {
      drawedCards = drawedCards :+ cards(i)
    }
    val newDeck = copy(cards.dropRight(num))
    (newDeck, drawedCards)
  }

  def initDeck(): Vector[CardInterface] = {
    for {
      number <- Vector(0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7,
        8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13 )
    }
      yield Card(false ,number)
  }

  def resetDeck(): Deck = {
    copy(Random.shuffle(initDeck()))
  }

  def cardsInDeck: Int = cards.size

  def getAllCards: Vector[CardInterface] = cards
}
