package de.htwg.se.model.playercomponent

import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.handcomponent.HandInterface

trait PlayerInterface {
  def toString: String
  def getPlayer: PlayerInterface
  def getHand: HandInterface
  def getName: String
  def setNewCard: CardInterface
  def getNewCard: CardInterface
}
