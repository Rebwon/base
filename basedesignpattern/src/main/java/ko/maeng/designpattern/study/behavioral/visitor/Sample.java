package ko.maeng.designpattern.study.behavioral.visitor;

import java.util.List;

public class Sample {

  public static void main(String[] args) {
    List<SmartPhone> phones = List.of(new Gallaxy(), new Iphone());
    Game game = new Game();
    phones.forEach(game::play);
  }
}

interface SmartPhone {
   void game(Game game);
}

class Iphone implements SmartPhone {

  @Override
  public void game(Game game) {
    System.out.println("iphone play [" + this.getClass().getSimpleName() + "]");
  }
}

class Gallaxy implements SmartPhone {

  @Override
  public void game(Game game) {
    System.out.println("gallaxy play [" + this.getClass().getSimpleName() + "]");
  }
}


class Game {
  public void play(SmartPhone phone) {
    phone.game(this);
  }
}