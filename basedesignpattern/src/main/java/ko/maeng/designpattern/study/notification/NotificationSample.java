package ko.maeng.designpattern.study.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationSample {

  public Notification validate() {
    final Notification notification = new Notification();
    if("Something".equals("Something")) {
      notification.addError("Something Errors");
    }

    return notification;
  }

  class Notification {
    private final List<String> errors = new ArrayList<>();

    public void addError(final String message) {
      errors.add(message);
    }

    public boolean hasErrors() {
      return !errors.isEmpty();
    }

    public String errorMessage() {
      return errors.toString();
    }

    public List<String> getErrors() {
      return this.errors;
    }
  }
}
