package ko.maeng.designpattern.study.concurrency.pd;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;

class ProducerTest {

  @Test
  void testProduce() {
    assertTimeout(ofMillis(6000), () -> {
      final var queue = mock(ItemQueue.class);
      final var producer = new Producer(queue, "producer");

      producer.produce();
      verify(queue).put(any(Item.class));

      verifyNoMoreInteractions(queue);
    });
  }
}