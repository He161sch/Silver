package de.htwg.se.model.playercomponent.playerbaseimpl

import de.htwg.se.model.cardcomponent.CardInterface
import de.htwg.se.model.cardcomponent.cardbaseimlp.Card
import de.htwg.se.model.handcomponent.HandInterface
import de.htwg.se.model.playercomponent.PlayerInterface

case class Player (name: String, hand: HandInterface, var newCard: CardInterface) extends PlayerInterface {

  override def toString: String = name + "'s hand: " + hand.toString

  def getPlayer: PlayerInterface = this

  def getHand: HandInterface = hand

  def getName: String = name

  def getNewCard: CardInterface = newCard
  def setNewCard: CardInterface = {
    newCard = Card(false, 14)
    newCard
  }


}
