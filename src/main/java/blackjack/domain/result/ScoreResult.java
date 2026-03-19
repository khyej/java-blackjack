package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.User;
import java.util.List;

public record ScoreResult(String name, List<Card> cards, int score) {
    public static ScoreResult from(User user) {
        return new ScoreResult(
                user.getName(),
                user.cards(),
                user.getScore()
        );
    }
}
