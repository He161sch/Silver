package model

case class Player(name: String, hand: Hand) {
  //var hands: Hand = Hand(List(randomCard(), randomCard(), randomCard(), randomCard(), randomCard()))
  override def toString:String = name
  def randomCard(): Card = {
    val r = scala.util.Random
    val card = Card(r.nextInt(14))
    card
  }
}

