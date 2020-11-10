package model


case class Hand(cards: List[Card]){
  override def toString: String = {
    var output = "["
    output += cards.mkString(", ")
    output += "]\n"
    output
  }
  def handValue(): Int = {
    var sum: Int = 0
    for(card <- cards){
      sum += card.number
    }
    sum
  }
}

