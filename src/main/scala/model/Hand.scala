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
  def removeAtIdx[T](idx: Int, listToRemoveFrom: List[T]): List[T] = {
    assert(listToRemoveFrom.length > idx && idx >= 0)
    val (left, _ :: right) = listToRemoveFrom.splitAt(idx)
    left ++ right
  }

}

