package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameSummary;
import java.util.List;

public class OutputView {
    public void printInitCards(List<Player> players, Dealer dealer) {
        System.out.println();
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        System.out.println(String.format("딜러와 %s에게 %d장을 나누었습니다.", String.join(", ", names), Deck.INITIAL_CARD_COUNT));

        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private void printDealerCard(Dealer dealer) {
        Card card = dealer.cards().getFirst();
        System.out.println(String.format("딜러카드: %s", card.format()));
    }

    public void printPlayerCards(Player player) {
        List<String> formats = player.cards().stream()
                .map(Card::format)
                .toList();
        System.out.println(String.format("%s카드: %s", player.getName(), String.join(", ", formats)));
    }


    public void printDealerHit() {
        System.out.println();
        System.out.println(String.format("딜러는 %d미만이라 한장의 카드를 더 받았습니다.", Dealer.DEALER_STAND_SCORE));
    }

    public void printGameSummary(List<GameSummary> gameSummaries) {
        System.out.println();
        for (GameSummary gameSummary : gameSummaries) {
            List<String> cardFormats = gameSummary.cards().stream()
                    .map(Card::format)
                    .toList();
            System.out.println(String.format("%s카드: %s - 결과: %d", gameSummary.name(), String.join(", ", cardFormats),
                    gameSummary.score()));
        }
    }

    public void printGameResult(List<GameResult> gameResults) {
        System.out.println();
        System.out.println("## 최종 수익");
        for (GameResult gameResult : gameResults) {
            System.out.println(String.format("%s: %d", gameResult.name(), gameResult.profit()));
        }
    }

    public void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

}

