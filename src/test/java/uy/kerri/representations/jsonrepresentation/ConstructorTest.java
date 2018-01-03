package uy.kerri.representations.test.jsonrepresentation;

import uy.kerri.representations.impl.JSONRepresentation;

import org.junit.Assert;
import org.junit.Test;
import org.apache.commons.lang3.StringUtils;

public class ConstructorTest {
  @Test
  public void testEmptyJSON() {
    Assert.assertEquals(
      "El JSON generado no está vacío.",
      "{}", new JSONRepresentation().toString()
    );
  }

  @Test
  public void testFromJSON() {
    String json = StringUtils.join(
      "{",
        "\"name\":\"Pablo\",",
        "\"age\":37",
      "}"
    );
    Assert.assertEquals(
      "El JSON generado no es igual al proporcionado.",
      json,
      new JSONRepresentation(json).toString()
    );
  }
}
