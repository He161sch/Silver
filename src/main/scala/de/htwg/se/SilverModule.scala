
package de.htwg.se



import com.google.inject.AbstractModule
import de.htwg.se.controller.controllercomponent.ControllerInterface
import de.htwg.se.controller.controllercomponent.controllerbaseimpl.Controller
import de.htwg.se.model.fileiocomponent.FileIOInterface
import de.htwg.se.model.fileiocomponent.fileiojasonimpl.Json
import de.htwg.se.model.fileiocomponent.fileioxmlimpl.Xml
import de.htwg.se.model.gameconfigcomponent.GameConfigInterface
import de.htwg.se.model.gameconfigcomponent.gameconfigbaseimpl.GameConfig
import net.codingwell.scalaguice.ScalaModule


class SilverModule extends AbstractModule with ScalaModule{

  override def configure() = {

    bind[ControllerInterface].to[Controller]
    bind[GameConfigInterface].toInstance(GameConfig())
//    bind[FileIOInterface].to[Json]
    bind[FileIOInterface].to[Xml]


  }
}
