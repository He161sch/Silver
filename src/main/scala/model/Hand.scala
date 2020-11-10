package model

case class Hand(cards: List[Card]){
  def size: Int = 5
  override def toString: String = {
    var output = "["
    output += cards.mkString(", ")
    output += "]\n"
    output
  }
}

