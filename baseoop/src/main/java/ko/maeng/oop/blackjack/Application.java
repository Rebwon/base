package ko.maeng.oop.blackjack;

public class Application {
    public static void main(String[] args) {
        Client client = new Client();
        Game game = new Game(client);
        game.play();
    }
}
