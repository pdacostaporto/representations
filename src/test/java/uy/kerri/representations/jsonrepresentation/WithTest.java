package uy.kerri.representations.test.jsonrepresentation;

import uy.kerri.representations.impl.JSONRepresentation;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public class WithTest {
  @Test
  public void testWithInteger() {
    Integer number = 2;
    String key = "integer";
    Assert.assertEquals(
      "El formato del JSON con entero no es correcto.",
      String.format("{\"%s\":%s}", key, number),
      new JSONRepresentation().with(key, number).toString()
    );
  }

  @Test
  public void testWithDouble() {
    Double number = 3.5;
    String key = "double";
    Assert.assertEquals(
      "El formato del JSON con punto flotante no es correcto.",
      String.format("{\"%s\":%s}", key, number),
      new JSONRepresentation().with(key, number).toString()
    );
  }

  @Test
  public void testWithLong() {
    Long number = 3003230432334220L;
    String key = "long";
    Assert.assertEquals(
      "El formato del JSON con entero largo no es correcto.",
      String.format("{\"%s\":%s}", key, number),
      new JSONRepresentation().with(key, number).toString()
    );
  }

  @Test
  public void testWithFalseBoolean() {
    Boolean bool = false;
    String key = "false";
    Assert.assertEquals(
      "El formato del JSON con booleano falso no es correcto.",
      String.format("{\"%s\":%s}", key, bool),
      new JSONRepresentation().with(key, bool).toString()
    );
  }

  @Test
  public void testWithTrueBoolean() {
    Boolean bool = true;
    String key = "true";
    Assert.assertEquals(
      "El formato del JSON con booleano verdadero no es correcto.",
      String.format("{\"%s\":%s}", key, bool),
      new JSONRepresentation().with(key, bool).toString()
    );
  }

  @Test
  public void testWithString() {
    String string = "Esto es una cadena de caracteres.";
    String key = "string";
    Assert.assertEquals(
      "El formato del JSON con cadena de caracteres no es correcto.",
      String.format("{\"%s\":\"%s\"}", key, string),
      new JSONRepresentation().with(key, string).toString()
    );
  }

  @Test
  public void testWithObject() {
    ArrayList<Object> list = new ArrayList<Object>();
    String key = "list";
    Assert.assertEquals(
      "El formato del JSON con un objeto cualquiera no es correcto.",
      String.format("{\"%s\":%s}", key, list),
      new JSONRepresentation().with(key, list).toString()
    );
  }

  @Test
  public void testWithObjectArray() {
    String key = "arreglo de objetos";
    Object[] array = {1, "s", 3.99, 1L, true};
    Assert.assertEquals(
      "El formato del JSON con un iterable de objetos no es correcto.",
      String.format(
        "{\"%s\":[%d,\"%s\",%s,%d,%b]}",
        key, array[0], array[1], array[2], array[3], array[4]
      ),
      new JSONRepresentation().with(key, array).toString()
    );
  }

  @Test
  public void testImmutableWithObjects() {
    JSONRepresentation representation = new JSONRepresentation();
    representation.with("valor", 100);
    Assert.assertEquals(
      StringUtils.join(
        "El formato del JSON original es modificado al usarse para ",
        "representar con un objeto cualquiera."
      ),
      "{}",
      representation.toString()
    );
  }

  @Test
  public void testImmutableWithArrayOfObjects() {
    JSONRepresentation representation = new JSONRepresentation();
    Object[] paises = { "Uruguay", "Argentina", "Bolivia" };
    representation.with("paises", paises);
    Assert.assertEquals(
      StringUtils.join(
        "El formato del JSON original es modificado al usarse para ",
        "representar con un arreglo de objetos."
      ),
      "{}",
      representation.toString()
    );
  }
}
