package blackjack.domain.card;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public String format() {
        return rank.getName() + suit.getName();
    }

}
