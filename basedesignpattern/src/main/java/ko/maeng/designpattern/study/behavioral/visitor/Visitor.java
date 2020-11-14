package ko.maeng.designpattern.study.behavioral.visitor;

public abstract class Visitor {
  public abstract void visitTextNode(TextNode textNode);
  public abstract void visitImageNode(ImageNode imageNode);
}
