package ko.maeng.designpattern.study.concurrency.pd;

public class Item {
  private final String producer;
  private final int id;

  public Item(String producer, int id) {
    this.producer = producer;
    this.id = id;
  }

  public String getProducer() {
    return producer;
  }

  public int getId() {
    return id;
  }
}
