package model

case class Card(number: Int) {
  var showing: Boolean = false
  override def toString: String = {
    "%d".format(number)
  }
}
