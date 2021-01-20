package de.htwg.se.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec with Matchers {

  "An Observable" should {
    val observable = new Observable
    val observer = new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Boolean = {updated = true; updated}
    }
    "add an Observer" in {
      observable.add(observer)
      observable.subscribers should contain (observer)
    }
    "remove a Observer" in {
      observable.remove(observer)
      observable.subscribers should not contain (observer)
    }
    "notify a Observer" in {
      observer.isUpdated should be(false)
      observable.notifyObservers
      observer.isUpdated should be(false)
    }
  }
}
