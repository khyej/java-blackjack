package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.FixShuffleStrategy;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    private BlackjackGame blackjackGame;
    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(List.of(new Player("pobi", 1000)));

        List<Card> fixCards = List.of(
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.SIX)
        );
        Deck deck = new Deck(new FixShuffleStrategy(fixCards));

        blackjackGame = new BlackjackGame(players, deck);
    }

    @Test
    @DisplayName("플레이어는 초기 카드 분배 후 21점 미만이면 hit 가능")
    void test_initCards_canPlayerHit() {
        blackjackGame.initCards();
        List<Player> gamePlayers = blackjackGame.getPlayers();
        Player player = gamePlayers.get(0);

        assertThat(blackjackGame.canPlayerHit(player)).isTrue();

    }

    @Test
    @DisplayName("딜러는 초기 카드 분배 후 17점 미만이면 hit 가능")
    void test_initCards_canDealerHit() {
        blackjackGame.initCards();

        assertThat(blackjackGame.canDealerHit()).isTrue();
    }
}
