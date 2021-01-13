package de.htwg.se.model.cardcomponent.cardbaseimlp


import de.htwg.se.model.cardcomponent.CardInterface

case class Card (var visible: Boolean, number: Int) extends CardInterface {
  override def toString: String = {
    "%d".format(number)
  }

  def getNumber: Int = number

  def setVisibility(boolean: Boolean): Unit = visible = boolean

  def getVisibility: Boolean = visible
}