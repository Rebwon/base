package ko.maeng.oop.policy;

public interface Policy<T, R> {

  boolean evaluate(T t, PolicyConditions conditions);

  R apply(T t, PolicyConditions conditions);
}
