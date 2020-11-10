package aview

import model.{Hand, Player, Card}

class TUI {

  def processInputLine(input: String, player: Player, newCard: Card): Player = {
    val inputsplit = input.split(" ").toList
    inputsplit match{
      case _ =>
        if (inputsplit.head.matches("d") && inputsplit(1).matches("1|2|3|4|0")){
          printf("card value: %d \n", player.hand.cards(inputsplit(1).toInt).number)
        }else if (inputsplit.head.matches("s") && inputsplit(1).matches("1|2|3|4|0")){
          val x = inputsplit(1).toInt
          println(x)
          val newplayer = Player(player.name, Hand(player.hand.cards.patch(inputsplit(1).toInt, List(newCard), 1)))
          return newplayer
        }
    }
    player
  }

}

