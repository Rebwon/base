package oop.blackjack;

import ko.maeng.oop.blackjack.Card;
import ko.maeng.oop.blackjack.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    private CardDeck cardDeck;
    private Stack<Card> cards;

    @BeforeEach
    void setup(){
        cardDeck = new CardDeck();
        cards = cardDeck.getCards();
    }

    @Disabled
    @DisplayName("카드패턴 비교")
    @Test
    void generateCardDeck() {
        assertThat(cards.get(0).getPattern()).isEqualTo(Card.Pattern.SPADE);
        assertThat(cards.get(13).getPattern()).isEqualTo(Card.Pattern.HEART);
    }

    @Disabled
    @DisplayName("카드끗수 비교")
    @Test
    void denominationCompare() {
        assertThat(cards.get(0).getDenomination()).isEqualTo(Card.Denomination.ACE);
        assertThat(cards.get(13).getDenomination().getPoint()).isEqualTo(1);
    }

    @DisplayName("stack으로 변환")
    @Test
    void changeStack(){
        assertThat(cardDeck.getCards().size()).isEqualTo(52);
        cardDeck.draw();
        assertThat(cardDeck.getCards().size()).isEqualTo(51);
        cardDeck.draw();
        assertThat(cardDeck.getCards().size()).isEqualTo(50);
        cardDeck.draw();
        assertThat(cardDeck.getCards().size()).isEqualTo(49);
    }
}
