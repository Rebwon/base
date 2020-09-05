package ko.maeng.designpattern.study.structural.frontcontroller;

public class CatapultCommand implements Command {

  @Override
  public void process() {
    new CatapultView().display();
  }
}
