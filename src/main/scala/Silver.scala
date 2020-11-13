import model.{Card, Hand, Player}

import scala.io.StdIn._
import aview.TUI

import scala.::

object Silver {

  def main(args: Array[String]): Unit = {
    println("Welcome to Silver\n")
    //val board = gameBoard()
    //println(board)
    var p1 = Player("player1", randomHand())
    var input: String = ""
    val tui = new TUI
    do{
      var newCard = p1.randomCard()
      printf("new card = %d\n", newCard.number)
      println(p1.name +"'s hand: " + p1.hand.toString)
      input = readLine()
      p1 = tui.processInputLine(input, p1, newCard)
    } while (input != "q")

  }


  def gameBoard(): String = {
    var board = ""
    val p1 = Player("player1", randomHand())
    val p2 = Player("player2", randomHand())
    val c1 = Card(1)
    val c2 = Card(3)
    val c3 = Card(10)
    val c4 = Card(2)
    val c5 = Card(7)
    val cards = List(c1, c2, c3, c4, c5)
    val hand = Hand(cards)

    board = board.concat(p1.hand.toString)
    board = board.concat(p1.hand.toString)
    board = board.concat(p1.hand.toString)
    board = board.concat(p1.toString())
    board = board.concat(hand.toString())
    board = board.concat(p2.toString())
    board = board.concat(hand.toString())

    board


  }
  def randomHand(): Hand ={
    val r = scala.util.Random
    var cards = List[Card]()
    for(x <- 0 to 4){
      cards = Card(r.nextInt(14)) :: cards
    }
    val hand = Hand(cards)
    hand
  }
}
