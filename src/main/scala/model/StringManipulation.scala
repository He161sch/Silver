package model

object StringManipulation {

  def makeBoard(): String = {

    val r = scala.util.Random
    val player1 = "player 1"
    val player2 = "player 2"

    val cardc = "|_|"
    var discard = "| ||_|"

    var board = " "
    var hand1 = " "
    var hand2 = " "

    for(x <- 0 to 4) {
      hand1 = hand1.concat(cardc)
      hand2 = hand2.concat(cardc)
    }

    discard = discard.replaceAll(" ", r.nextInt(13).toString)

    board = board.concat("\t").concat(player1).concat("\n").concat(hand1).concat("\n\n")
    board = board.concat("\t ").concat(discard).concat("\n\n")
    board = board.concat(hand2).concat("\n\t").concat(player2)
    board
  }

  def main(args: Array[String]): Unit = {
    val board = makeBoard()
    println(board)
  }

}

