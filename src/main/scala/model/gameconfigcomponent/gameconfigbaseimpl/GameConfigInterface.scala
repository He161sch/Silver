package model.gameconfigcomponent.gameconfigbaseimpl

import model.cardcomponent.cardbaseimlp.Card
import model.gameconfigcomponent.GameConfigInterface
import model.handcomponent.handbaseimpl.Hand
import model.playercomponent.playerbaseimpl
import model.playercomponent.playerbaseimpl.Player
import model.deckcomponent.deckbaseimpl.Deck

import com.google.inject.Inject

case class GameConfig (players: Vector[Player], deck: Deck, activePlayerIdx: Int = 0, winners: Vector[Player] = Vector[Player]()) extends GameConfigInterface {

  def createPlayer(playerName: String = ""): GameConfig = {
    val (newDeck, newHand) = deck.drawCards(5)
    val player = playerbaseimpl.Player(playerName, Hand(newHand), Card(14))
    copy(players :+ player, newDeck)
  }

  def setPlayerName(playerName: String, playerIdx: Int): GameConfig = {
    val newPlayer = playerbaseimpl.Player(playerName, players(playerIdx).hand, Card(14))
    updatePlayerAtIdx(newPlayer, playerIdx, deck)
  }

  def updatePlayerAtIdx(newPlayer: Player, idx: Int, newDeck: Deck): GameConfig = {
    var newPlayers = Vector[Player]()
    for (i <- players.indices) {
      if (i == idx) {
        newPlayers = newPlayers :+ newPlayer
      } else {
        newPlayers = newPlayers :+ players(i)
      }
    }
    GameConfig(newPlayers, newDeck, activePlayerIdx)
  }


  def viewCard(idx: Int): GameConfig = {
    val viewedCard = players(activePlayerIdx).hand.cards(idx)
    println("Your Card on place " + idx + " is: " + viewedCard)
    updatePlayerAtIdx(players(activePlayerIdx), activePlayerIdx, deck)
  }

  def drawCard(): GameConfig = {
    val (newDeck, drawedCard) = deck.drawCards(1)
    val newPlayer = Player(players(activePlayerIdx).name, players(activePlayerIdx).hand, drawedCard(0))

    updatePlayerAtIdx(newPlayer, activePlayerIdx, newDeck)
  }

  def switchCard(idx: Int): GameConfig = {
    val drawedCard = players(activePlayerIdx).newCard
    val newPlayer = playerbaseimpl.Player(players(activePlayerIdx).name, Hand(players(activePlayerIdx).hand.cards.updated(idx, drawedCard)), Card(14))

    updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)
  }

  def combineCard(idx1: Int, idx2: Int): GameConfig = {
    val drawedCard = players(activePlayerIdx).newCard

    if (players(activePlayerIdx).hand.cards(idx1).number.equals(players(activePlayerIdx).hand.cards(idx2).number)) {
      val np = playerbaseimpl.Player(players(activePlayerIdx).name, Hand(players(activePlayerIdx).hand.cards.updated(idx1, drawedCard)), Card(14))
      val newPlayer = playerbaseimpl.Player(np.name, Hand(np.hand.removeAtIdx(idx2, np.hand.cards)), Card(14))
      updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)
    } else {
      updatePlayerAtIdx(players(activePlayerIdx), activePlayerIdx, deck)
    }
  }

  def addWinner(winner: Player): GameConfig = {
    val isWinner = winners :+ winner
    copy(players, deck, 0, isWinner)
  }

  def winnerToString(): String = {
    val sb = new StringBuilder
    if (winners.size == 1) {
      sb.append(winners(0).name).append(" has won with a total of ").append(winners(0).hand.handValue())
        .append(" points\n")
    }
    sb.toString()
  }

  def incrementActivePlayerIdx(): GameConfig = {
    copy(players, deck, activePlayerIdx + 1)
  }

  def resetActivePlayerIdx(): GameConfig = {
    copy(players, deck, 0)
  }

  def getActivePlayerName = players(activePlayerIdx).name

  def getActivePlayer = players(activePlayerIdx)


}

