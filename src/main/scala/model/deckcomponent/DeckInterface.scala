package model.deckcomponent

import model.cardcomponent.cardbaseimlp.Card
import model.deckcomponent.deckbaseimpl.Deck

import scala.util.Random

trait DeckInterface {

  def drawCards(num: Int): (Deck, Vector[Card])

  def initDeck(): Vector[Card]

  def resetDeck(): Deck
}
