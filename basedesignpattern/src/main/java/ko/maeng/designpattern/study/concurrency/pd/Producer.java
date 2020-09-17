package ko.maeng.designpattern.study.concurrency.pd;

import java.util.Random;

public class Producer {
  private static final Random RANDOM = new Random();
  private final ItemQueue queue;
  private final String name;
  private int itemId;

  public Producer(ItemQueue queue, String name) {
    this.queue = queue;
    this.name = name;
  }

  public void produce() throws InterruptedException {
    var item = new Item(name, itemId++);
    queue.put(item);
    Thread.sleep(RANDOM.nextInt(2000));
  }
}
