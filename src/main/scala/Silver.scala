
import scala.io.StdIn._
import aview.{TUI, UIInterface, GUI}
import controller.{Controller, State}


object Silver extends Runnable{

  val controller = new Controller()



  def run(): Unit = {
    var input: String = ""

//    println("Which interface do you want to use? [TUI,GUI]")
//    val iface = readLine()
//    if (iface != "TUI" && iface != "GUI" ) {
//      println("There is only TUI and GUI")
//      println("Try again with one of those")
//    }
    val uiinterface = UIInterface("TUI" , controller)

    controller.notifyObservers(State.WelcomeState)
    do{
      input = readLine()
      uiinterface.inputCommand(input)
    } while (input != "q")
  }







  def main(args: Array[String]): Unit = {

    Silver.run()
  }
}