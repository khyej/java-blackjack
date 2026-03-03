package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardCalculatorTest {
    @Test
    @DisplayName("카드 합계 테스트")
    void testCardScore() {
        List<String> cards = List.of("K", "3", "4");
        CardCalculator calculator = new CardCalculator(cards);

        assertThat(calculator.cardScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("카드 합계 경계 테스트")
    void testCardScoreBoundary(){
        List<String> cards = List.of("");
        CardCalculator calculator = new CardCalculator(cards);

        assertThat(calculator.cardScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("카드 합계 음수 테스트")
    void testCardScoreMinus(){
        List<String> cards = List.of("-10", "5", "4");
        CardCalculator calculator = new CardCalculator(cards);

        assertThat(calculator.cardScore()).isEqualTo(9);
    }
}

