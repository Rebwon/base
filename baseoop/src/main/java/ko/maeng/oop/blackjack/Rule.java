package ko.maeng.oop.blackjack;

import java.util.List;

public class Rule {
    public int getPointSum(List<Card> cards){
        int sum = 0;
        for(Card card : cards){
            sum += card.getPoint();
        }
        return sum;
    }

    public Player getWinner(List<Player> players) {
        Player higherPlayer = null;
        int highestPoint = 0;

        for(Player player : players){
            int playerPointSum = getPointSum(player.openCards());
            if(playerPointSum > highestPoint){
                higherPlayer = player;
                highestPoint = playerPointSum;
            }
        }
        return higherPlayer;
    }
}
