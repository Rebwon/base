package ko.maeng.designpattern.study.behavioral.visitor;

public class TextNode implements Node {
  private StringBuilder text = new StringBuilder();

  public TextNode() {}

  public TextNode(String s) {
    text.append(s);
  }

  public String getText() {
    return text.toString();
  }

  public void setText(String text) {
    this.text.setLength(0);
    this.text.append(text);
  }

  public int wordCount() {
    return 0;
  }

  public String getAsDraft() {
    return null;
  }

  @Override
  public void accept(Visitor v) {
    v.visitTextNode(this);
  }
}
