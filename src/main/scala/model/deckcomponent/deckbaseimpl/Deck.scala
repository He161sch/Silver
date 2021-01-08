package model.deckcomponent.deckbaseimpl

import model.cardcomponent.cardbaseimlp.Card
import model.deckcomponent.DeckInterface

import com.google.inject.Inject
import scala.util.Random

case class Deck (cards: Vector[Card]) extends DeckInterface {
  def this() = this(Vector[Card]())

  def drawCards(num: Int): (Deck, Vector[Card]) = {
    var drawedCards = Vector[Card]()

    val from = cards.size - num
    val bis = cards.size - 1

    for (i <- (from to bis)) {
      drawedCards = drawedCards :+ cards(i)
    }
    val newDeck = copy(cards.dropRight(num))
    (newDeck, drawedCards)
  }

  def initDeck(): Vector[Card] = {
    for {
      number <- Vector(0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7,
        8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13 )
    }
      yield Card(number)
  }

  def resetDeck(): Deck = {
    copy(Random.shuffle(initDeck()))
  }
}
