package ko.maeng.designpattern.study.structural.frontcontroller;

public class FrontController {

  public void handleRequest(String request) {
    var command = getCommand(request);
    command.process();
  }

  private Command getCommand(String request) {
    var commandClass = getCommandClass(request);
    try {
      return (Command) commandClass.newInstance();
    } catch (Exception e) {
      throw new ApplicationException(e);
    }
  }

  private static Class<?> getCommandClass(String request) {
    try {
      return Class.forName("ko.maeng.designpattern.study.structural.frontcontroller." + request + "Command");
    } catch (ClassNotFoundException e) {
      return UnknownCommand.class;
    }
  }
}
