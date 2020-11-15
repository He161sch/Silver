package model


case class Hand(cards: List[Card]){
  override def toString: String = "[" + cards.mkString(", ") + "]\n"
  //def handValue(): Int = cards.foldLeft(0)(_ + _) //Verbessern fehler finden!
  def handValue(): Int ={
    var sum: Int = 0
    for (card <- cards){
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
