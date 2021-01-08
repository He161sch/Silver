package controller

import controller.controllercomponent.{IsNotRunning, IsRunning}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StateSpec extends AnyWordSpec with Matchers {
  "State " when {
    "is IsRunning and parameter is also IsRunning" should {
      "give the output" in {
        val running = IsRunning()
        val (state, output) = running.handle(IsRunning())
        state should be(IsRunning())
        output should be("Game is running!")
      }
    }
    "is IsRunning and parameter is IsNotRunning" should {
      "give the output" in {
        val running = IsRunning()
        val (state, output) = running.handle(IsNotRunning())
        state should be(IsNotRunning())
        output should be("Game is not running!")
      }
    }
    "is IsNotRunning and parameter is IsRunning" should {
      "give the output" in {
        val isNotRunning = IsNotRunning()
        val (state, output) = isNotRunning.handle(IsRunning())
        state should be(IsRunning())
        output should be("Game is running!")
      }
    }
    "is IsNotRunning and parameter is IsNotRunning" should {
      "give the output" in {
        val isNotRunning = IsNotRunning()
        val (state, output) = isNotRunning.handle(IsNotRunning())
        state should be(IsNotRunning())
        output should be("Game is not running!")
      }
    }
  }
}
