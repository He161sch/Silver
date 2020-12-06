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

  def getActivePlayerName = players(activePlayerIdx).name

  def getActivePlayer = players(activePlayerIdx)

  def incrementActivePlayerIdx(): GameConfig = {
    copy(players, deck, activePlayerIdx + 1)
  }

  def resetActivePlayerIdx(): GameConfig = {
    copy(players, deck, 0)
  }





}
