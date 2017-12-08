package uy.kerri.representations.test.jsonrepresentation;

import uy.kerri.representations.impl.JSONRepresentation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class WithMultipleKeysTest {
  private JSONObject json;
  private Integer count = 7;
  private String countKey = "cantidad";
  private String rock = "Esta noche toca Pier.";
  private String rockKey = "quién toca esta noche???";
  private Integer[] numbers = { 7, 24, 9 };
  private String numbersKey = "numeros";

  @Before
  public void testWithMultipleKeys() {
    this.json = new JSONObject(
      new JSONRepresentation()
      .with(this.countKey, this.count)
      .with(this.rockKey, this.rock)
      .with(this.numbersKey, this.numbers)
      .toString()
    );
  }

  @Test
  public void testNumberArray() {
    Assert.assertEquals(
      StringUtils.join(
        "El formato del arreglo de números dentro del JSON con múltiples",
        "claves no es correcto.",
        ' '
      ),
      String.format(
        "[%d,%d,%d]",
        this.numbers[0], this.numbers[1], this.numbers[2]
      ),
      this.json.getJSONArray(this.numbersKey).toString()
    );
  }

  @Test
  public void testString() {
    Assert.assertEquals(
      StringUtils.join(
        "El formato de la cadena de caracteres dentro del JSON con múltiples",
        "claves no es correcto.",
        ' '
      ),
      rock,
      this.json.getString(this.rockKey)
    );
  }

  @Test
  public void testNumber() {
    Assert.assertEquals(
      StringUtils.join(
        "El formato del número dentro del JSON con múltiples claves no es",
        "correcto.",
        ' '
      ),
      count,
      new Integer(this.json.getInt(this.countKey))
    );
  }

  @Test
  public void testLength() {
    Assert.assertEquals(
      "La cantidad de claves del JSON no es correcta.",
      3,
      this.json.length()
    );
  }
}
