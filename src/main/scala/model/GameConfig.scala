package model

import scala.util.Random

case class GameConfig(players: Vector[Player] , deck: Deck, activePlayerIdx: Int = 0, winner: Vector[Player] = Vector[Player]()){

  def createPlayer(playerName: String = ""): GameConfig = {
    val (newDeck, newHand) = deck.drawCards(5)
    val player = Player(playerName, Hand(newHand))
    copy(players :+ player, newDeck)
  }

  def setPlayerName(playerName: String, playerIdx: Int): GameConfig = {
    val newPlayer = Player(playerName, players(playerIdx).hand)
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

  def getActivePlayerName = players(activePlayerIdx).name

  def getActivePlayer = players(activePlayerIdx)

  def incrementActivePlayerIdx(): GameConfig = {
    copy(players, deck, activePlayerIdx + 1)
  }

  def resetActivePlayerIdx(): GameConfig = {
    copy(players, deck, 0)
  }





}
