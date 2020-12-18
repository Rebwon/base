package ko.maeng.base.java.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Generic<T> {

  protected Class<T> classT;
  protected T objectT;

  @SuppressWarnings("unchecked")
  public Generic() {
    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();

    Type type = genericSuperclass.getActualTypeArguments()[0];

    if(type instanceof ParameterizedType) {
      this.classT = (Class) ((ParameterizedType) type).getRawType();
    } else {
      this.classT = (Class) type;
    }

    try {
      this.objectT = classT.getDeclaredConstructor().newInstance();
    } catch(InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
