import model.{Card, Player, Hand}

object Silver {

  def main(args: Array[String]): Unit = {
    println("Welcome to Silver\n")
    val board = gameBoard()
    println(board)
  }


  def gameBoard(): String = {
    var board = ""
    val p1 = Player("player1")
    val p2 = Player("player2")
    val c1 = Card(1)
    val c2 = Card(3)
    val c3 = Card(10)
    val c4 = Card(2)
    val c5 = Card(7)
    val cards = Vector(c1, c2, c3, c4, c5)
    val hand = Hand(cards)

    board = board.concat(p1.toString())
    board = board.concat(hand.toString())//.concat("\n")
    board = board.concat(p2.toString())
    board = board.concat(hand.toString())

    board


  }
}