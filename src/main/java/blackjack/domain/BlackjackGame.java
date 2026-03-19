package blackjack.domain;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameSummary;
import java.util.List;


public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Deck deck) {
        this.players = players;
        this.dealer = new Dealer();
        this.deck = deck;
    }

    public void initCards() {
        deck.provideInitCards(players, dealer);
    }

    public boolean canPlayerHit(Player player) {
        return player.canHit();
    }

    public boolean canDealerHit() {
        return dealer.canHit();
    }

    public void hitPlayer(Player player) {
        deck.provideOneCard(player);
    }

    public void hitDealer() {
        deck.provideOneCard(dealer);
    }

    public List<GameSummary> calculateGameSummaries() {
        return players.calculateGameSummaries(dealer);
    }

    public List<GameResult> calculateGameResults() {
        return players.calculateGameResults(dealer);
    }

    public List<Player> getPlayers() {
        return players.all();
    }

    public Dealer getDealer() {
        return dealer;
    }
}

