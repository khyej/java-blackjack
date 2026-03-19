package blackjack.domain.participant;

import blackjack.domain.result.GameJudge;
import blackjack.domain.result.GameOutcome;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ScoreResult;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final int PLAYER_LIMIT = 7;

    private final List<Player> players;

    public Players(List<Player> allPlayers) {
        validateDuplicate(allPlayers);
        validatePlayerCount(allPlayers);
        this.players = allPlayers;
    }

    public List<Player> all() {
        return players;
    }

    public List<ScoreResult> calculateScoreResults(Dealer dealer) {
        List<ScoreResult> scoreResults = new ArrayList<>();
        scoreResults.add(ScoreResult.from(dealer));
        players.forEach(player -> scoreResults.add(ScoreResult.from(player)));
        return scoreResults;
    }

    public List<ProfitResult> calculateProfitResults(Dealer dealer) {
        GameJudge gameJudge = new GameJudge();
        List<ProfitResult> profitResults = calculatePlayerProfitResults(gameJudge, dealer);
        int totalPlayerProfit = profitResults.stream()
                .mapToInt(ProfitResult::profit)
                .sum();

        profitResults.addFirst(ProfitResult.from(dealer, -totalPlayerProfit));
        return profitResults;
    }

    private List<ProfitResult> calculatePlayerProfitResults(GameJudge gameJudge, Dealer dealer) {
        List<ProfitResult> playerProfitResults = new ArrayList<>();
        for (Player player : players) {
            GameOutcome outcome = gameJudge.judge(player, dealer);
            int playerProfit = player.getBet().calculateProfit(outcome.getPayoutRate());
            playerProfitResults.add(ProfitResult.from(player, playerProfit));
        }
        return playerProfitResults;
    }

    private void validateDuplicate(List<Player> allPlayers) {
        long unique = allPlayers.stream().map(Player::getName).distinct().count();
        if (unique != allPlayers.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(List<Player> allPlayers) {
        if (allPlayers.size() > PLAYER_LIMIT) {
            throw new IllegalArgumentException("플레이어의 최대 인원은 7명입니다.");
        }
    }
}
