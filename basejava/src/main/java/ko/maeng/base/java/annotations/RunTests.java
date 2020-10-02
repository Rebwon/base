package ko.maeng.base.java.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

  public static void main(String[] args) throws Exception {
    int tests = 0;
    int passed = 0;
    Class testClass = Class.forName(args[0]);
    for(Method m : testClass.getDeclaredMethods()){
      if(m.isAnnotationPresent(Test.class)) {
        tests++;
        try {
          m.invoke(null);
          System.out.printf("Test %s failed: no exception%n", m);
        } catch (Throwable wrappedExc) {
          Throwable exc = wrappedExc.getCause();
          Class<? extends Exception>[] excTypes = m.getAnnotation(ExceptedTest.class).value();
          int oldPassed = passed;
          for(Class<? extends Exception> excType : excTypes) {
            if(excType.isInstance(exc)) {
              passed++;
              break;
            }
          }
          if (passed == oldPassed) {
            System.out.printf("Test %s failed: %s %n %n", m, exc);
          }
        }
      }
    }
  }
}
