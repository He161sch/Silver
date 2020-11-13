package controller

import model.{Hand, Card, Player}
import util.Observable

class Controller() extends Observable {

  def switchCard(player:Player, idx: Int, newCard: Card): Player = {
    val newPlayer = Player(player.name, Hand(player.hand.cards.patch(idx, List(newCard), 1)))
    notifyObservers
    newPlayer
  }

  def combineCard(player: Player, idx1: Int, idx2: Int, newCard: Card): Player ={
    if(player.hand.cards(idx1).number.equals(player.hand.cards(idx2).number)){
      val hand = Hand(player.hand.cards.updated(idx1, newCard))
      val newPlayer = Player(player.name, Hand(player.hand.removeAtIdx(idx2, hand.cards)))
      notifyObservers
      newPlayer
    } else {
      printf("card values are not the same! (%d, %d)\n",player.hand.cards(idx1).number, player.hand.cards(idx2).number)
      notifyObservers
      player
    }
  }

  def playerToString: String = Player.toString()
}
