package model

case class Card(number: Int) { // konstruktor showing!
  override def toString: String = {
    "%d".format(number)
  }
}
