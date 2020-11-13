package model

case class Player(name: String, hand: Hand) {
  override def toString:String = name + "'s hand: " + hand.toString
  def randomCard(): Card = {
    val r = scala.util.Random
    val card = Card(r.nextInt(14))
    card
  }
  def getPlayer: Player = this
}

