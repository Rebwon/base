package ko.maeng.oop.elgant;

public class Cash {
  private Number dollars;

  public Cash(String dlr) {
    this.dollars = new StringAsInteger(dlr);
  }
}
