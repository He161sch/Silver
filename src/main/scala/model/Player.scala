package model

case class Player(name: String, hand: Hand) {
  override def toString:String = name
  def randomCard(): Card = {
    val r = scala.util.Random
    val card = Card(r.nextInt(1))
    card
  }
}

