package ko.maeng.oop.blackjack.domain;

import java.util.Stack;

public interface Player {
    void receiveCard(Card card);

    Stack<Card> openCards();

    void turnOff();

    void turnOn();

    boolean isTurn();

    String getName();
}
