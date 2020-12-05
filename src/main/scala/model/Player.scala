package model

case class Player(name: String, hand: Hand)  {
  override def toString:String = name + "'s hand: " + hand.toString

  def getPlayer: Player = this
}

