package util
import controller.State

trait Observer {
  def update(status: State.Value): Boolean
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(status: State.Value): Unit = subscribers.foreach(observer => observer.update(status: State.Value))

}