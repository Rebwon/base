package ko.maeng.base.java.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operator {
  PLUS("+"),
  MINUS("-"),
  DIVIDE("/"),
  MULTIPLY("*");

  // Caching
  private static final Map<String, Operator> OPERATOR_MAP = Collections.unmodifiableMap(Stream.of(values())
      .collect(Collectors.toMap(Operator::getRepresentation, Function.identity())));
  private final String representation;

  Operator(String representation) {
    this.representation = representation;
  }

  // 메서드 호출이 잦거나, enum의 갯수가 많아진다면, 성능이 좋지 않은 메서드이다.
  public static Operator of(String input) {
    return Arrays.stream(Operator.values())
        .filter(o -> o.representation.equals(input))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("연산자를 찾을 수 없습니다."));
  }

  // O(1)의 복잡도 of메서드보다 약 20배 빠른 조회 성능을 보장.
  public static Operator find(String input) {
    return Optional.ofNullable(OPERATOR_MAP.get(input))
        .orElseThrow(() -> new IllegalArgumentException("연산자를 찾을 수 없습니다."));
  }

  public String getRepresentation() {
    return representation;
  }
}
