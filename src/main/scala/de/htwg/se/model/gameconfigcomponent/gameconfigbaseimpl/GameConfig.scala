package de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl

import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.model.handcomponent.handbaseimpl.Hand
import de.htwg.se.model.playercomponent.{PlayerInterface, playerbaseimpl}
import de.htwg.se.model.playercomponent.playerbaseimpl.Player
import de.htwg.se.model.deckcomponent.deckbaseimpl.Deck
import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.deckcomponent.DeckInterface

case class GameConfig (players: Vector[PlayerInterface] = Vector[PlayerInterface](),
                       deck: DeckInterface = new Deck().resetDeck(),
                       var discardDeck: CardInterface = Card(false, 15), activePlayerIdx: Int = 0,
                       winners: Vector[PlayerInterface] = Vector[PlayerInterface]()) extends GameConfigInterface {

  def createPlayer(playerName: String = ""): GameConfigInterface = {
    val (newDeck, newHand) = deck.drawCards(5)
    val player = playerbaseimpl.Player(playerName, Hand(newHand), Card(false, 14))
    copy(players :+ player, newDeck)
  }

  def setPlayerName(playerName: String, playerIdx: Int): GameConfigInterface = {
    val newPlayer = playerbaseimpl.Player(playerName, players(playerIdx).getHand, Card(false, 14))
    players(activePlayerIdx).getHand.getCard(0).setVisibility(true)
    players(activePlayerIdx).getHand.getCard(1).setVisibility(true)
    updatePlayerAtIdx(newPlayer, playerIdx, deck)
  }

  def updatePlayerAtIdx(newPlayer: PlayerInterface, idx: Int, newDeck: DeckInterface): GameConfigInterface = {
    var newPlayers = Vector[PlayerInterface]()
    for (i <- players.indices) {
      if (i == idx) {
        newPlayers = newPlayers :+ newPlayer
      } else {
        newPlayers = newPlayers :+ players(i)
      }
    }
    GameConfig(newPlayers, newDeck, discardDeck, activePlayerIdx)
  }


  def viewCard(idx: Int): GameConfigInterface = {
    val viewedCard = players(activePlayerIdx).getHand.getCard(idx)

    println("Your Card on place " + idx + " is: " + viewedCard)
    updatePlayerAtIdx(players(activePlayerIdx), activePlayerIdx, deck)
  }

  def drawCard(): GameConfigInterface = {
    val (newDeck, drawedCard) = deck.drawCards(1)
    val newPlayer = Player(players(activePlayerIdx).getName, players(activePlayerIdx).getHand, drawedCard(0))


    updatePlayerAtIdx(newPlayer, activePlayerIdx, newDeck)
  }

  def drawFromDiscard(): GameConfigInterface = {

    val newPlayer = Player(players(activePlayerIdx).getName, players(activePlayerIdx).getHand, discardDeck)
    discardDeck = Card(true, 15)
    updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)
  }

  def switchCard(idx: Int): GameConfigInterface = {
    val drawedCard = players(activePlayerIdx).getNewCard
    val newPlayer = Player(players(activePlayerIdx).getName, Hand(players(activePlayerIdx).getHand.getAllCards.updated(idx, drawedCard)), Card(false, 14))
    updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)
  }

  def combineCard(idx1: Int, idx2: Int): GameConfigInterface = {
    val drawedCard = players(activePlayerIdx).getNewCard

    if (players(activePlayerIdx).getHand.getCard(idx1).getNumber.equals(players(activePlayerIdx).getHand.getCard(idx2).getNumber)
      || players(activePlayerIdx).getHand.getCard(idx1).getNumber == 13
      || players(activePlayerIdx).getHand.getCard(idx2).getNumber == 13) {

      val np = Player(players(activePlayerIdx).getName, Hand(players(activePlayerIdx).getHand.getAllCards.updated(idx1, drawedCard)), Card(false, 14))
      val newPlayer = Player(np.name, Hand(np.hand.removeAtIdx(idx2, np.hand.getAllCards)), Card(false, 14))
      updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)

    } else {
      discardDeck = drawedCard
      updatePlayerAtIdx(players(activePlayerIdx), activePlayerIdx, deck)
    }

  }

  def addWinner(winner: PlayerInterface): GameConfigInterface = {
    val isWinner = winners :+ winner
    copy(players, deck, discardDeck,0, isWinner)
  }

//  def allPlayersToString(): String = {
//    val sb = new StringBuilder
//    if (players.size == 3) {
//      sb.append(players(0).getName).append(" has a total of ").append(players(0).getHand.handValue())
//        .append(" points\n")
//      sb.append(players(1).getName).append(" has a total of ").append(players(0).getHand.handValue())
//        .append(" points\n")
//      sb.append(players(2).getName).append(" has a total of ").append(players(0).getHand.handValue())
//        .append(" points\n")
//
//    } else if (players.size == 2) {
//      sb.append(players(0).getName).append(" has a total of ").append(players(0).getHand.handValue())
//        .append(" points\n")
//      sb.append(players(1).getName).append(" has a total of ").append(players(0).getHand.handValue())
//        .append(" points\n")
//    }
//    sb.toString()
//  }


  def winnerToString(): String = {
    val sb = new StringBuilder
    if (winners.size == 1) {
      sb.append(winners(0).getName).append(" has won with a total of ").append(winners(0).getHand.handValue())
        .append(" points\n")
    }
    sb.toString()
  }

  def incrementActivePlayerIdx(): GameConfigInterface = {
    copy(players, deck, discardDeck, activePlayerIdx + 1)
  }

  def resetActivePlayerIdx(): GameConfigInterface = {
    copy(players, deck, discardDeck,0)
  }

  def getActivePlayerName: String = players(activePlayerIdx).getName

  def getActivePlayer: PlayerInterface = players(activePlayerIdx)

  def getPlayerAtIdx(idx: Int): PlayerInterface = players(idx)

  def getActivePlayerIdx: Int = activePlayerIdx

  def getPlayerSize: Int = players.size

  def getAllPlayers: Vector[PlayerInterface] = players

  def getWinners: Vector[PlayerInterface] = winners

  def getDeck: DeckInterface = deck

  def getDiscardDeck: CardInterface = discardDeck

  def setDiscardDeck: CardInterface = {
    discardDeck = players(activePlayerIdx).getNewCard
    discardDeck
  }

  def replaceDiscardDeck(idx: Int): CardInterface = {
    discardDeck = players(activePlayerIdx).getHand.getCard(idx)
    discardDeck
  }

  def restartGame(): GameConfigInterface = {
    val resetedGameConfig = GameConfig(Vector[PlayerInterface](), deck.resetDeck(),Card(false, 15), 0, Vector[PlayerInterface]())

    resetedGameConfig
  }
}