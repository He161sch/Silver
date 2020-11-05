import model.Player

object Silver {
  def main(args: Array[String]): Unit = {
    val player1 = Player("Player1")
    val player2 = Player("Player2")
    println("Hello, " + player1.name + " and " + player2.name)
  }
}
