package de.htwg.se.model.deckcomponent

import de.htwg.se.model.cardcomponent.CardInterface

import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck

import scala.util.Random

trait DeckInterface {

  def drawCards(num: Int): (Deck, Vector[CardInterface])
  def initDeck(): Vector[CardInterface]
  def resetDeck(): DeckInterface
  def cardsInDeck: Int
  def getAllCards: Vector[CardInterface]
}
