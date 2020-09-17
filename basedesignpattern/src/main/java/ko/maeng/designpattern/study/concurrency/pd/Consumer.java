package ko.maeng.designpattern.study.concurrency.pd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {
  private static final Logger log = LoggerFactory.getLogger(Consumer.class);
  private final ItemQueue queue;
  private final String name;

  public Consumer(ItemQueue queue, String name) {
    this.queue = queue;
    this.name = name;
  }

  public void consume() throws InterruptedException {
    var item = queue.take();
    log.info("Consumer [{}] consume item [{}] produced by [{}]", name,
        item.getId(), item.getProducer());
  }
}
