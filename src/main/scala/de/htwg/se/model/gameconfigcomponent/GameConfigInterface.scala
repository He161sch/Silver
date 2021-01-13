package de.htwg.se.model.gameconfigcomponent

import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.deckcomponent.DeckInterface
import de.htwg.se.model.playercomponent.PlayerInterface


trait GameConfigInterface {

  def createPlayer(playerName: String = ""): GameConfigInterface
  def setPlayerName(playerName: String, playerIdx: Int): GameConfigInterface
  def updatePlayerAtIdx(newPlayer: PlayerInterface, idx: Int, newDeck: DeckInterface): GameConfigInterface
  def viewCard(idx: Int): GameConfigInterface
  def drawCard(): GameConfigInterface
  def drawFromDiscard(): GameConfigInterface
  def switchCard(idx: Int): GameConfigInterface
  def combineCard(idx1: Int, idx2: Int): GameConfigInterface
  def addWinner(winner: PlayerInterface): GameConfigInterface
  def winnerToString(): String
  def incrementActivePlayerIdx(): GameConfigInterface
  def resetActivePlayerIdx(): GameConfigInterface
  def getPlayerAtIdx(idx: Int): PlayerInterface
  def getActivePlayerIdx: Int
  def getPlayerSize: Int
  def getAllPlayers: Vector[PlayerInterface]
  def getActivePlayer: PlayerInterface
  def getActivePlayerName: String
  def getWinners: Vector[PlayerInterface]
  def getDeck: DeckInterface
  def getDiscardDeck: CardInterface
  def replaceDiscardDeck(idx: Int): CardInterface
  def restartGame(): GameConfigInterface

}
