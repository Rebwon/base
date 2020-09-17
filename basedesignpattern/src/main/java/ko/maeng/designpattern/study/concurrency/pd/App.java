package ko.maeng.designpattern.study.concurrency.pd;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger log = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    var queue = new ItemQueue();

    var executorService = Executors.newFixedThreadPool(5);

    for (var i = 0; i < 2; i++) {

      final var producer = new Producer(queue,"Producer_" + i);
      executorService.submit(() -> {
        while (true) {
          producer.produce();
        }
      });
    }

    for (var i = 0; i < 3; i++) {
      final var consumer = new Consumer(queue,"Consumer_" + i);
      executorService.submit(() -> {
        while (true) {
          consumer.consume();
        }
      });
    }

    executorService.shutdown();
    try {
      executorService.awaitTermination(10, TimeUnit.SECONDS);
      executorService.shutdownNow();
    } catch (InterruptedException e) {
      log.error("Error waiting for ExecutorService shutdown");
    }
  }

}
