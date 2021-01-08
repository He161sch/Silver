package model.playercomponent.playerbaseimpl

import com.google.inject.Inject
import model.cardcomponent.cardbaseimlp.Card
import model.handcomponent.handbaseimpl.Hand
import model.playercomponent.PlayerInterface

case class Player (name: String, hand: Hand, newCard: Card) extends PlayerInterface {

  override def toString: String = name + "'s hand: " + hand.toString

  def getPlayer: Player = this
}
