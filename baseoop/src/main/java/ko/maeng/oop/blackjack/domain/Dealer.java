package ko.maeng.oop.blackjack.domain;

import ko.maeng.oop.blackjack.exception.OutOfPointException;

import java.util.Stack;

public class Dealer implements Player{
    private Stack<Card> cards;
    private boolean turn;

    private static final int CAN_RECEIVE_POINT = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        cards = new Stack<>();
    }

    public void receiveCard(Card card) {
        if(this.isReceiveCard()){
            this.cards.push(card);
        } else{
            throw new OutOfPointException("카드의 총 합이 17이상입니다. 더이상 카드를 받을 수 없습니다.");
        }
    }

    private boolean isReceiveCard() {
        return getPointSum() <= CAN_RECEIVE_POINT;
    }

    private int getPointSum() {
        int sum = 0;
        for(Card card : cards){
            sum += card.getDenomination().getPoint();
        }
        return sum;
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
        return NAME;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
