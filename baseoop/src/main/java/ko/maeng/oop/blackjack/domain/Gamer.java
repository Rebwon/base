package ko.maeng.oop.blackjack.domain;


import java.util.Stack;

public class Gamer implements Player{
    private Stack<Card> cards;
    private boolean turn;
    private String name;

    public Gamer(String name) {
        cards = new Stack<>();
        this.name = name;
    }

    public void receiveCard(Card card) {
        this.cards.push(card);
    }

    public Stack<Card> openCards() {
        return this.cards;
    }

    @Override
    public void turnOff() {
        this.setTurn(false);
    }

    @Override
    public void turnOn() {
        this.setTurn(true);
    }

    @Override
    public boolean isTurn() {
        return this.turn;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
