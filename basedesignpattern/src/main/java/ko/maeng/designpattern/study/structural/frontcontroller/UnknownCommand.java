package ko.maeng.designpattern.study.structural.frontcontroller;

public class UnknownCommand implements Command {

  @Override
  public void process() {
    new ErrorView().display();
  }
}
