package ko.maeng.designpattern.study.structural.frontcontroller;

public class ErrorView implements View {

  @Override
  public void display() {
    System.out.println("Error 500");
  }
}
