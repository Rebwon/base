package ko.maeng.designpattern.study.structural.frontcontroller;

public class App {

  public static void main(String[] args) {
    var controller = new FrontController();
    controller.handleRequest("Archer");
    controller.handleRequest("Catapult");
    controller.handleRequest("Rebwon");
  }

}
