package model.cardcomponent.cardbaseimlp

import com.google.inject.Inject
import model.cardcomponent.CardInterface



case class Card (number: Int) extends CardInterface {
  override def toString: String = {
    "%d".format(number)
  }
}
