package controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StateSpec extends AnyWordSpec with Matchers{
  "State" when {
    "is IsRunning" should {
      "give output" in {
        val running = IsRunning()
        val (state, output) = running.handle(IsRunning())
        state should be (IsRunning())
        output should be ("Game is running!")
      }
    }
    "is IsNotRunning" should {
      "give output" in {
        val notRunning = IsNotRunning()
        val (state, output) = notRunning.handle(IsNotRunning())
        state should be (IsNotRunning())
        output should be ("Game is not running!")
      }
    }
    "is IsRunning but with Parameter IsNotRunning" should {
      "give output" in {
        val running = IsRunning()
        val (state, output) = running.handle(IsNotRunning())
        state should be (IsNotRunning())
        output should be ("Game is not running!")
      }
    }
    "is IsNotRunning but with Parameter IsRunning" should {
      "give output" in {
        val notRunning = IsNotRunning()
        val (state, output) = notRunning.handle(IsRunning())
        state should be (IsRunning())
        output should be ("Game is running!")
      }
    }
  }
}
