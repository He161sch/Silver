import com.google.inject.AbstractModule
import controller.controllercomponent._
import model.cardcomponent._
import model.deckcomponent._
import model.gameconfigcomponent._
import model.handcomponent._
import model.playercomponent._
import net.codingwell.scalaguice.ScalaModule


class SilverModule extends AbstractModule with ScalaModule{

  override def configure() = {


//    bind[DeckInterface].to[deckbaseimpl.Deck]
//    bind[HandInterface].to[handbaseimpl.Hand]
//    bind[CardInterface].to[cardbaseimlp.Card]
//    bind[PlayerInterface].to[playerbaseimpl.Player]
//    bind[GameConfigInterface].to[gameconfigbaseimpl.GameConfig]
    bind[ControllerInterface].to[controllerbaseimpl.Controller]


  }
}
