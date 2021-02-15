package ko.maeng.oop.elgant;

import java.util.ArrayList;
import java.util.List;

public class CachedNumber implements Number {
  private final Number origin;
  private final List<Integer> cached = new ArrayList<>(1);

  public CachedNumber(Number number) {
    this.origin = number;
  }

  public int intValue() {
    if(this.cached.isEmpty()) {
      this.cached.add(this.origin.intValue());
    }
    return this.cached.get(0);
  }
}
