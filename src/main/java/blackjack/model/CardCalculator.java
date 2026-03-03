package blackjack.model;

import java.util.List;

public class CardCalculator {
    private final List<String> cards;

    public CardCalculator(List<String> cards){
        this.cards = cards;
    }

    public int cardScore(){
        int totalScore = 0;
        for(String card : cards){
             if(isNumeric(card)){
                 int score = Integer.parseInt(card);

                 if( score >= 2 && score <= 10){
                     totalScore += score;
                 }
                 continue;
             }

             if (card.equals("J") || card.equals("Q") || card.equals("K")){
                 totalScore += 10;
                 continue;
             }

             if(card.equals("A")){
                 // A 처리 로직
             }

        }
        return totalScore;
    }

    public static boolean isNumeric(String cardName){
        try {
            Integer.parseInt(cardName);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    // 테스트용 함수
    public static void main(String[] args) {
        testCase("test1", List.of("K", "5", "4"));
        testCase("test2", List.of("", "5", "4"));
        testCase("test3", List.of("0", "5", "4"));
        testCase("test4", List.of("A", "5", "4"));
        testCase("test5", List.of("4", "14", "4"));
        testCase("test6", List.of("-10", "5", "4"));
    }

    public static void testCase(String testName, List<String> cards) {
        CardCalculator calculator = new CardCalculator(cards);
        System.out.println(testName + " : " + calculator.cardScore());
    }

}
