//package model
//
//import model.cardcomponent.cardbaseimlp.Card
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//
//class DeckSpec extends AnyWordSpec with Matchers {
//  "The Deck" when {
//    "new" should {
//    val deck = new Deck()
//    "be empty" in {
//      deck.cards.size should be(0)
//    }
//    "have unapply" in {
//      var deck = new Deck()
//      deck = Deck(deck.initDeck())
//      Deck.unapply(deck).get should be (deck.cards)
//    }
//  }}
//
//  "The Deck" when {
//    "initialized" should {
//    var deck = new Deck()
//    deck = Deck(deck.initDeck())
//    "have 52 cards" in {
//      deck.cards.size should be(52)
//    }
//  }}
//
//  "The Deck" when {
//    "a card is drawn" should {
//    var deck = Deck(Vector(Card(0), Card(1), Card(2), Card(3), Card(4)))
//    val (newDeck, cards) = deck.drawCards(1)
//    deck = newDeck
//    "have draw card" in {
//      deck.cards.size should be(4)
//      cards(0) should be(Card(4))
//    }
//  }}
//
//  "The Deck" when {
//    "multiple cards are drawn" should {
//    var deck = Deck(Vector(Card(0), Card(1), Card(2), Card(3), Card(4)))
//
//    val (newDeck, cards) = deck.drawCards(4)
//    deck = newDeck
//    "have drawn multiple cards" in {
//      deck.cards.size should be(1)
//      cards.size should be(4)
//    }
//  }}
//
//  "The Deck" when {
//    "is reseted" should {
//    var deck = new Deck(Vector(Card(2), Card(5)))
//    "has only 2 cards before reset" in {
//      deck.cards.size should be(2)
//    }
//    "have 52 cards" in {
//      deck = deck.resetDeck()
//      deck.cards.size should be (52)
//    }
//  }}
//}
