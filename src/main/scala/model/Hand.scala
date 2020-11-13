package model


case class Hand(cards: List[Card]){
  override def toString: String = "[" + cards.mkString(", ") + "]\n"
  def handValue(): Int = cards.foldLeft(0)(_ + _) //Verbessern fehler finden!
}
