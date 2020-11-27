

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class SilverSpec extends AnyWordSpec with Matchers {

  "The Silver main class" should {
    "accept text input as argument without readline loop, to test it from command line " in {
      Silver.main(Array[String]("d"))
    }
  }

}