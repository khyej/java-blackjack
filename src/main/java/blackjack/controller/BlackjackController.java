package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ScoreResult;
import blackjack.view.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Deck deck;

    public BlackjackController(InputView inputView, OutputView outputView, Deck deck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck = deck;
    }

    public void run() {
        Players players = setupPlayers();
        BlackjackGame blackjackGame = new BlackjackGame(players, deck);

        displayInitCards(blackjackGame);

        hitTurns(blackjackGame);

        displayScoreResults(blackjackGame);
        displayProfitResults(blackjackGame);

        inputView.closeScanner();
    }

    private Players setupPlayers() {
        List<String> playerNames = retry(() -> {
            String input = inputView.readPlayerName();
            return InputParser.parse(input);
        });

        List<Player> allPlayers = new ArrayList<>();

        for (String playerName : playerNames) {
            int betAmount = retry(() -> inputView.readBetAmount(playerName));
            allPlayers.add(new Player(playerName, betAmount));
        }

        return new Players(allPlayers);
    }

    private void displayInitCards(BlackjackGame blackjackGame) {
        blackjackGame.initCards();
        outputView.printInitCards(blackjackGame.getPlayers(), blackjackGame.getDealer());
    }

    private void hitTurns(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            while (blackjackGame.canPlayerHit(player) && retry(() -> inputView.readCardAdd(player))) {
                blackjackGame.hitPlayer(player);
                outputView.printPlayerCards(player);
            }
        }

        while (blackjackGame.canDealerHit()) {
            outputView.printDealerHit();
            blackjackGame.hitDealer();
        }
    }

    private void displayScoreResults(BlackjackGame blackjackGame) {
        List<ScoreResult> scoreResults = blackjackGame.calculateScoreResults();
        outputView.printScoreResults(scoreResults);
    }

    private void displayProfitResults(BlackjackGame blackjackGame) {
        List<ProfitResult> profitResults = blackjackGame.calculateProfitResults();
        outputView.printProfitResults(profitResults);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}

