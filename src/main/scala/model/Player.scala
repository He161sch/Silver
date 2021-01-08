package model

case class Player(name: String, hand: Hand, newCard: Card)  {

  override def toString:String = name + "'s hand: " + hand.toString

  def getPlayer: Player = this
}

