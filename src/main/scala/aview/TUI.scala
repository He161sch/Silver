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
          val x = inputsplit(1).toInt
          println(x)
          val newPlayer = Player(player.name, Hand(player.hand.cards.patch(inputsplit(1).toInt, List(newCard), 1)))
          return newPlayer
        } else if (inputsplit.head.matches("e")){  //end
          printf("your points: %d", player.hand.handValue())
          System.exit(0)
        } else if (inputsplit.head.matches("c") && inputsplit(1).matches("1|2|3|4|0") && inputsplit(2).matches("1|2|3|4|0")) {   //combine
          if(player.hand.cards(inputsplit(1).toInt).number.equals(player.hand.cards(inputsplit(2).toInt).number)){
            val hand = Hand(player.hand.cards.updated(inputsplit(1).toInt, newCard))
            val newPlayer = Player(player.name, Hand(removeAtIdx(inputsplit(2).toInt, hand.cards)))
            return newPlayer
          } else {
            printf("card values are not the same! (%d, %d)\n",player.hand.cards(inputsplit(1).toInt).number, player.hand.cards(inputsplit(2).toInt).number)
          }

        }
    }
    player
  }

  def removeAtIdx[T](idx: Int, listToRemoveFrom: List[T]): List[T] = {
    assert(listToRemoveFrom.length > idx && idx >= 0)
    val (left, _ :: right) = listToRemoveFrom.splitAt(idx)
    left ++ right
  }

}

