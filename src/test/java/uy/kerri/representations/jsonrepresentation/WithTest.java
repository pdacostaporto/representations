package uy.kerri.representations.test.jsonrepresentation;

import uy.kerri.representations.impl.JSONRepresentation;
import uy.kerri.representations.Representable;
import uy.kerri.representations.fake.FakeRepresentable;

import junit.framework.Assert;
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
  public void testWithRepresentable() {
    String key = "representable";
    FakeRepresentable representable =
        new FakeRepresentable("Algo corto", "Algo largo");
    Assert.assertEquals(
      "El formato del JSON con un representable no es correcto.",
      String.format(
        StringUtils.join(
          "{",
            "\"%s\":{",
              "\"%s\":\"%s\"",
            "}",
          "}"
        ),
        key,
        representable.summaryKey(), representable.summaryString()
      ),
      new JSONRepresentation().with(key, representable).toString()
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
  public void testWithRepresentableArray() {
    String key = "arreglo de representables";
    FakeRepresentable first =
        new FakeRepresentable("1ra", "1ra representación");
    FakeRepresentable second =
        new FakeRepresentable("2da", "2da representación");
    FakeRepresentable third =
        new FakeRepresentable("3ra", "3ra representación");
    Representable[] array = { first, second, third };
    Assert.assertEquals(
      "El formato del JSON con un iterable de representables no es correcto.",
      String.format(
        StringUtils.join(
          "{",
            "\"%s\":[",
              "{",
                "\"%s\":\"%s\"",
              "},{",
                "\"%s\":\"%s\"",
              "},{",
                "\"%s\":\"%s\"",
              "}",
            "]",
          "}"
        ),
        key,
        first.summaryKey(), first.summaryString(),
        second.summaryKey(), second.summaryString(),
        third.summaryKey(), third.summaryString()
      ),
      new JSONRepresentation().with(key, array).toString()
    );
  }

  /*
   * TODO: Este test depende del ordenamiento aleatorio que se hace del JSON.
   * Buscar una forma que sea independiente del orden.
   */
  @Test
  public void testWithMultipleKeys() {
    Integer count = 7;
    String count_key = "cantidad";
    String rock = "Esta noche toca Pier.";
    String rock_key = "quién toca esta noche???";
    FakeRepresentable something =
        new FakeRepresentable("Alguito", "Algote");
    String something_key = "algo";
    FakeRepresentable[] things = {
      new FakeRepresentable("El Faro", "El Faro de Ingeniería")
    };
    String things_key = "cosas";
    Integer[] numbers = { 7, 24, 9 };
    String numbers_key = "numeros";
    Assert.assertEquals(
      "El formato del JSON con múltiples claves no es correcto.",
      String.format(
        StringUtils.join(
          "{",
            "\"%s\":[%d,%d,%d],",
            "\"%s\":%d,",
            "\"%s\":\"%s\",",
            "\"%s\":[",
              "{",
                "\"%s\":\"%s\"",
              "}",
            "],",
            "\"%s\":{",
              "\"%s\":\"%s\"",
            "}",
          "}"
        ),
        numbers_key, numbers[0], numbers[1], numbers[2],
        count_key, count,
        rock_key, rock,
        things_key,
        things[0].summaryKey(), things[0].summaryString(),
        something_key,
        something.summaryKey(), something.summaryString()
      ),
      new JSONRepresentation()
      .with(count_key, count)
      .with(things_key, things)
      .with(rock_key, rock)
      .with(numbers_key, numbers)
      .with(something_key, something)
      .toString()
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
  public void testImmutableWithRepresentables() {
    JSONRepresentation representation = new JSONRepresentation();
    representation.with(
      "bebida",
      new FakeRepresentable(
        "Grapa con limón",
        "Qué es lo que se toma en Uruguay???"
      )
    );
    Assert.assertEquals(
      StringUtils.join(
        "El formato del JSON original es modificado al usarse para ",
        "representar con un representable."
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

  @Test
  public void testImmutableWithArrayOfRepresentable() {
    JSONRepresentation representation = new JSONRepresentation();
    Representable[] food = {
      new FakeRepresentable("Asado", "Un cacho de carne"),
      new FakeRepresentable("Moñitas", "Fideos con forma de moña")
    };
    representation.with("food", food);
    Assert.assertEquals(
      StringUtils.join(
        "El formato del JSON original es modificado al usarse para ",
        "representar con un arreglo de representables."
      ),
      "{}",
      representation.toString()
    );
  }
}
