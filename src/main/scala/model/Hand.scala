package model

case class Hand(cards: Vector[Card]){
  override def toString: String = {
    var output = "["
    output += cards.mkString(", ")
    output += "]\n"
    output
  }
}

