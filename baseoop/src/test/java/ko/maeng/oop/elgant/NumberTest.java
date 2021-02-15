package ko.maeng.oop.elgant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberTest {

  @Test
  void cachingNumber() {
    Number number = new CachedNumber(
        new StringAsInteger("5")
    );
    int result = number.intValue();   // parsing
    int actual = number.intValue();   // caching
    assertThat(result).isEqualTo(actual);
  }
}