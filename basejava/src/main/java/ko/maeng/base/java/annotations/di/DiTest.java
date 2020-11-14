package ko.maeng.base.java.annotations.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DiTest {

  public static void main(String[] args)
      throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Component component = new Component();
    DiTest.inject(component);
    component.getService1().method();
    component.getService2().method();
  }

  private static void inject(Component component)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    for(Field field : component.getClass().getDeclaredFields()) {
      if(field.isAnnotationPresent(Autowired.class)) {
        Constructor<?> constructor = field.getType().getDeclaredConstructor();
        Object o = constructor.newInstance();
        boolean isAccessible = field.canAccess(component);
        field.setAccessible(true);
        field.set(component, o);
        field.setAccessible(isAccessible);
      }
    }
  }
}
