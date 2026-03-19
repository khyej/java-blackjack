package blackjack.domain;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ScoreResult;
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

    public List<ScoreResult> calculateScoreResults() {
        return players.calculateScoreResults(dealer);
    }

    public List<ProfitResult> calculateProfitResults() {
        return players.calculateProfitResults(dealer);
    }

    public List<Player> getPlayers() {
        return players.all();
    }

    public Dealer getDealer() {
        return dealer;
    }
}

