package ko.maeng.oop.blackjack;

import ko.maeng.oop.blackjack.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Game {
    private static final int INIT_RECEIVE_CARD_COUNT = 2;
    private static final String STOP_RECEIVE_CARD = "0";
    private Client client;

    public Game(Client client) {
        this.client = client;
    }

    public void play(){
        client.print("========BlackJack========");
        client.print("처음 2장의 카드를 각자 뽑겠습니다.");
        Rule rule = new Rule();
        CardDeck cardDeck = new CardDeck();

        List<Player> players = Arrays.asList(new Gamer("사용자1"), new Dealer());
        List<Player> initAfterPlayers = initPhase(cardDeck, players);
        List<Player> playingAfterPlayers = playingPhase(cardDeck, initAfterPlayers);

        Player winner = rule.getWinner(playingAfterPlayers);
        client.print("승자는 " + winner.getName());
    }

    private List<Player> initPhase(CardDeck cardDeck, List<Player> players) {
        for(int i=0; i<INIT_RECEIVE_CARD_COUNT; i++){
            for(Player player : players){
                Card card = cardDeck.draw();
                player.receiveCard(card);
                showCards(player.getName(), player.openCards());
            }
        }
        return players;
    }

    private void showCards(String name, Stack<Card> cards){
        StringBuilder sb = new StringBuilder();
        sb.append("현재 보유 카드 목록 " + name + ": \n");

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");
        }

        client.print(sb.toString());
    }

    private List<Player> playingPhase(CardDeck cardDeck, List<Player> players) {
        List<Player> cardOwningPlayers;
        while (true){
            cardOwningPlayers = allPlayersReceiveCard(cardDeck, players);

            if(allPlayersEndTurn(cardOwningPlayers)){
                break;
            }
        }
        return cardOwningPlayers;
    }

    private List<Player> allPlayersReceiveCard(CardDeck cardDeck, List<Player> players) {
        for(Player player : players){
            client.print(player.getName()+"님 차례입니다.");

            if(isDrawCard()){
                Card card = cardDeck.draw();
                player.receiveCard(card);
                showCards(player.getName(), player.openCards());
                player.turnOn();
            } else{
                player.turnOff();
            }
        }
        return players;
    }

    private boolean allPlayersEndTurn(List<Player> players) {
        for(Player player : players){
            if(player.isTurn()) {
                return false;
            }
        }
        return true;
    }

    private boolean isDrawCard() {
        client.print("카드를 뽑겠습니까? 종료를 원하시면 0을 입력하세요.");
        return !STOP_RECEIVE_CARD.equals(client.input());
    }
}
