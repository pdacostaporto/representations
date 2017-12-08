package uy.kerri.representations.test.jsonrepresentation;

import uy.kerri.representations.exception.UnsupportedFormatException;
import uy.kerri.representations.fake.FakeRepresentation;
import uy.kerri.representations.impl.JSONRepresentation;
import uy.kerri.representations.Representation;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public class WithRepresentationTest {
  @Test
  public void testWithRepresentation() throws UnsupportedFormatException {
    String key = "representation";
    String representation = "{\"data\":\"some data\"}";
    Assert.assertEquals(
      "El formato del JSON con representación anidada no es correcto",
      String.format("{\"%s\":%s}", key, representation),
      new JSONRepresentation().with(
        key,
        new FakeRepresentation(representation)
      ).toString()
    );
  }

  @Test(expected = UnsupportedFormatException.class)
  public void testWithUnsupportedFormat() throws UnsupportedFormatException {
    String key = "nested";
    String representation = "\"data\":\"some other data\"";
    new JSONRepresentation().with(
      key,
      new FakeRepresentation(representation)
    );
  }

  @Test
  public void testWithRepresentationArray() throws UnsupportedFormatException {
    String key = "arreglo de representaciones";
    Representation[] array = {
      new FakeRepresentation("{\"day\":\"wednesday\"}"),
      new FakeRepresentation("{\"name\":\"Juan\"}"),
      new FakeRepresentation("{\"month\":\"october\"}")
    };
    Assert.assertEquals(
      "El formato del JSON con un arreglo de representaciones no es correcto.",
      String.format(
        "{\"%s\":[%s,%s,%s]}",
        key, array[0], array[1], array[2]
      ),
      new JSONRepresentation().with(key, array).toString()
    );
  }

  @Test(expected = UnsupportedFormatException.class)
  public void testWithUnsupportedArray() throws UnsupportedFormatException {
    String key = "arreglito";
    Representation[] array = {
      new FakeRepresentation("{\"day\":\"wednesday\"}"),
      new FakeRepresentation("{\"name\":\"Juan\"}"),
      new FakeRepresentation("month:october")
    };
    new JSONRepresentation().with(key, array).toString();
  }

  @Test
  public void testImmutableWithObjects() throws UnsupportedFormatException {
    JSONRepresentation representation = new JSONRepresentation();
    representation.with(
      "whatever",
      new FakeRepresentation("{\"children\":2}")
    );
    Assert.assertEquals(
      StringUtils.join(
        "El formato del JSON original es modificado al usarse para ",
        "representar con una representación anidadada."
      ),
      "{}",
      representation.toString()
    );
  }

  @Test
  public void testImmutableWithArrayOfObjects()
      throws UnsupportedFormatException {
    JSONRepresentation representation = new JSONRepresentation();
    Representation[] array = {
      new FakeRepresentation("{\"title\":\"Colombina\"}"),
      new FakeRepresentation("{\"author\":\"Jaime Roos\"}"),
    };
    representation.with("array", array);
    Assert.assertEquals(
      StringUtils.join(
        "El formato del JSON original es modificado al usarse para ",
        "representar con un arreglo de representaciones."
      ),
      "{}",
      representation.toString()
    );
  }
}
