package model

case class Card(number: Int) {
  def showing: Boolean = false
  override def toString: String = {
    "%d".format(number)
  }
}
