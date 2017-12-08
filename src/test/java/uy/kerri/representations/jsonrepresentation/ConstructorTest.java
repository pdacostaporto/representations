package uy.kerri.representations.test.jsonrepresentation;

import uy.kerri.representations.impl.JSONRepresentation;

import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest {
  @Test
  public void testGeneratedJSON() {
    Assert.assertEquals(
      "El JSON generado no está vacío.",
      "{}", new JSONRepresentation().toString()
    );
  }
}
