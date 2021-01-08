package model.gameconfigcomponent

import model.playercomponent.playerbaseimpl.Player
import model.deckcomponent.deckbaseimpl.Deck


trait GameConfigInterface {

  def createPlayer(playerName: String = ""): GameConfigInterface

  def setPlayerName(playerName: String, playerIdx: Int): GameConfigInterface

  def updatePlayerAtIdx(newPlayer: Player, idx: Int, newDeck: Deck): GameConfigInterface

  def viewCard(idx: Int): GameConfigInterface

  def drawCard(): GameConfigInterface

  def switchCard(idx: Int): GameConfigInterface

  def combineCard(idx1: Int, idx2: Int): GameConfigInterface

  def addWinner(winner: Player): GameConfigInterface

  def winnerToString(): String

  def incrementActivePlayerIdx(): GameConfigInterface

  def resetActivePlayerIdx(): GameConfigInterface


}
