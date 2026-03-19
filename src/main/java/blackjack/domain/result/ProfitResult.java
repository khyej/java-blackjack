package blackjack.domain.result;

import blackjack.domain.participant.User;

public record ProfitResult(String name, int profit) {
    public static ProfitResult from(User user, int profit) {
        return new ProfitResult(
                user.getName(),
                profit
        );
    }
}
