package ko.maeng.base.java.generic;

import java.util.HashMap;
import java.util.Map;

public class Favorites {
  private static final Map<Class<?>, Object> map = new HashMap<>();

  // 동적 형변환을 통해 실행시간 형 안정성 확보
  public <T> void putFavorites(Class<T> type, T instance) {
    map.put(type, type.cast(instance));
  }

  public <T> T getFavorites(Class<T> type) {
    return type.cast(map.get(type));
  }
}
