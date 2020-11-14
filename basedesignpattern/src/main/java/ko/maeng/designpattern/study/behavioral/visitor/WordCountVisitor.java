package ko.maeng.designpattern.study.behavioral.visitor;

public class WordCountVisitor extends Visitor {

  int wordCount = 0;

  @Override
  public void visitTextNode(TextNode textNode) {
    wordCount += wordCount(textNode.getText());
  }

  public int getWordCount() {
    return wordCount;
  }

  private int wordCount(String text) {
    if(text == null || text.trim().length() == 0)
      return 0;
    return text.trim().replaceAll("[^\\s]", "").length() + 1;
  }

  @Override
  public void visitImageNode(ImageNode imageNode) {
    if(imageNode.caption != null)
      visitTextNode(imageNode.caption);
  }
}
