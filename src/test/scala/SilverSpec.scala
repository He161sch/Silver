//
//
//import de.htwg.se.Silver
//
//import java.io.ByteArrayInputStream
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//
//
//class SilverSpec extends AnyWordSpec with Matchers {
//
//  "The Silver main class" should {
//    "accept text input as argument without readline loop, to test it from command line " in {
//      val input = new ByteArrayInputStream("q".getBytes)
//      Console.withIn(input) {
//        Silver.main(Array[String](""))
//      }
//    }
//  }
//
//}