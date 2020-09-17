package ko.maeng.designpattern.study.concurrency.pd;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class ConsumerTest {

  private static final int ITEM_COUNT = 5;

  @Test
  void testConsume() throws Exception {
    final var queue = spy(new ItemQueue());
    for (var id = 0; id<ITEM_COUNT; id++) {
      queue.put(new Item("producer", id));
    }

    reset(queue);
    final var consumer = new Consumer(queue, "Consumer");

    for (var id = 0; id < ITEM_COUNT; id++) {
      consumer.consume();
    }

    verify(queue, times(ITEM_COUNT)).take();
  }
}