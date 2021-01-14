package de.htwg.se.model.cardcomponent

trait CardInterface {
   def toString: String
   def getNumber: Int
   def setVisibility(boolean: Boolean): Unit
   def getVisibility: Boolean
}
