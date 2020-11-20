package model

case class Card(number: Int) {
  override def toString: String = {
    "%d".format(number)
  }
}
