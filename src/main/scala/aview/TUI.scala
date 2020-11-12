package aview

import model.{Hand, Player, Card}

class TUI {

  def processInputLine(input: String, player: Player, newCard: Card): Player = {
    val inputsplit = input.split(" ").toList
    inputsplit match{
      case _ =>
        if (inputsplit.head.matches("d") && inputsplit(1).matches("1|2|3|4|0")){
          printf("card value: %d \n", player.hand.cards(inputsplit(1).toInt).number)
        } else if (inputsplit.head.matches("s") && inputsplit(1).matches("1|2|3|4|0")){
          val newPlayer = switchCard(player, inputsplit(1).toInt, newCard)
          return newPlayer
        } else if (inputsplit.head.matches("e")){  //end
          printf("your points: %d", player.hand.handValue())
          System.exit(0)
        } else if (inputsplit.head.matches("c") && inputsplit(1).matches("1|2|3|4|0") && inputsplit(2).matches("1|2|3|4|0")) {   //combine
          val newPlayer = combineCard(player, inputsplit(1).toInt, inputsplit(2).toInt, newCard)
          return newPlayer
        }
    }
    player
  }

  def removeAtIdx[T](idx: Int, listToRemoveFrom: List[T]): List[T] = {
    assert(listToRemoveFrom.length > idx && idx >= 0)
    val (left, _ :: right) = listToRemoveFrom.splitAt(idx)
    left ++ right
  }

  def switchCard(player:Player, idx: Int, newCard: Card): Player = {
    val newPlayer = Player(player.name, Hand(player.hand.cards.patch(idx, List(newCard), 1)))
    newPlayer
  }

  def combineCard(player: Player, idx1: Int, idx2: Int, newCard: Card): Player ={
    if(player.hand.cards(idx1).number.equals(player.hand.cards(idx2).number)){
      val hand = Hand(player.hand.cards.updated(idx1, newCard))
      val newPlayer = Player(player.name, Hand(removeAtIdx(idx2, hand.cards)))
      newPlayer
    } else {
      printf("card values are not the same! (%d, %d)\n",player.hand.cards(idx1).number, player.hand.cards(idx2).number)
      player
    }
  }

}

