package util
//
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//
//class ObservableSpec extends AnyWordSpec with Matchers {
//
//  "An Observable" should {
//    val obserable = new Observable
//    val observer = new Observer {
//      var updated: Boolean = false
//      def isUpdated: Boolean = updated
//      override def update: Unit = updated = true
//    }
//    "add an Observer" in {
//      obserable.add(observer)
//      obserable.subscribers should contain (observer)
//    }
//    "remove a Observer" in {
//      obserable.remove(observer)
//      obserable.subscribers should not contain (observer)
//    }
//    "notify a Observer" in {
//      obserable.notifyObservers
//    }
//  }
//}
