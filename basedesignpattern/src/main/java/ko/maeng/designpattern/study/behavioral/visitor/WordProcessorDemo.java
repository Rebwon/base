package ko.maeng.designpattern.study.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class WordProcessorDemo {

  public static void main(String[] args) {
    List<Node> nodes = new ArrayList<>();
    nodes.add(new TextNode("My Thesis Dissertation"));
    nodes.add(new TextNode("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
    nodes.add(new ImageNode("ian-pro-headshot.png", "Ian May Not Be As Advertised"));

    Visitor wordCountVisitor = new WordCountVisitor();
    nodes.forEach(n -> n.accept(wordCountVisitor));
    System.out.printf("The document has approximately %d words%n", ((WordCountVisitor) wordCountVisitor).getWordCount());
  }

}
