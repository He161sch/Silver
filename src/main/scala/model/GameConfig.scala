package model


case class GameConfig(players: Vector[Player] , deck: Deck, activePlayerIdx: Int = 0, winner: Vector[Player] = Vector[Player]()){

  def createPlayer(playerName: String = ""): GameConfig = {
    val (newDeck, newHand) = deck.drawCards(5)
    val player = Player(playerName, Hand(newHand), Card(0))
    copy(players :+ player, newDeck)
  }

  def setPlayerName(playerName: String, playerIdx: Int): GameConfig = {
    val newPlayer = Player(playerName, players(playerIdx).hand, Card(0))
    updatePlayerAtIdx(newPlayer, playerIdx, deck)
  }

  def updatePlayerAtIdx(newPlayer: Player, idx: Int, newDeck: Deck): GameConfig = {
    var newPlayers = Vector[Player]()
    for (i <- players.indices) {
      if (i == idx) {
        newPlayers = newPlayers :+ newPlayer
      } else {
        newPlayers = newPlayers :+ players(i)
      }
    }
   GameConfig(newPlayers, newDeck, activePlayerIdx)
  }



  def viewCard(idx: Int): GameConfig = {
    val viewedCard = players(activePlayerIdx).hand.cards(idx)
    println("Your Card on place " + idx + " is: " + viewedCard)
    updatePlayerAtIdx(players(activePlayerIdx), activePlayerIdx, deck)
  }

  def drawCard(): GameConfig = {
    val (newDeck, drawedCard) = deck.drawCards(1)
    val newPlayer = Player(players(activePlayerIdx).name, players(activePlayerIdx).hand, drawedCard(0))

    updatePlayerAtIdx(newPlayer, activePlayerIdx, newDeck)
  }

  def switchCard(idx: Int): GameConfig = {
    val drawedCard = players(activePlayerIdx).newCard
    val newPlayer = Player(players(activePlayerIdx).name, Hand(players(activePlayerIdx).hand.cards.updated(idx, drawedCard)), Card(0))

    updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)
  }

  def combineCard(idx1: Int, idx2: Int): GameConfig = {
    val drawedCard = players(activePlayerIdx).newCard

    val np = Player(players(activePlayerIdx).name, Hand(players(activePlayerIdx).hand.cards.updated(idx1, drawedCard)), Card(0))
    val newPlayer = Player(np.name, Hand(np.hand.removeAtIdx(idx2, np.hand.cards)), Card(0))

    updatePlayerAtIdx(newPlayer, activePlayerIdx, deck)
  }


  def incrementActivePlayerIdx(): GameConfig = {
    copy(players, deck, activePlayerIdx + 1)
  }

  def resetActivePlayerIdx(): GameConfig = {
    copy(players, deck, 0)
  }

  def getActivePlayerName = players(activePlayerIdx).name

  def getActivePlayer = players(activePlayerIdx)


}
