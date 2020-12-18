package ko.maeng.base.java.generic;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenericTest {

  @Test
  public void typeInfer() {
    BookGeneric bookGeneric = new BookGeneric();
    assertNotNull(bookGeneric.objectT);
  }
}