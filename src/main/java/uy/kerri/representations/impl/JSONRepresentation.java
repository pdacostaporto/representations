package uy.kerri.representations.impl;

import uy.kerri.representations.exception.UnsupportedFormatException;
import uy.kerri.representations.Representation;

import org.json.*;

public class JSONRepresentation implements Representation {
  private JSONObject json;

  private JSONRepresentation(JSONObject json) {
    this.json = json;
  }

  public JSONRepresentation() {
    this(new JSONObject());
  }

  public JSONRepresentation(String json) {
    this(new JSONObject(json));
  }

  public Representation with(String key, Object value) {
    return new JSONRepresentation(addKey(key, value));
  }

  public Representation with(String key, Representation value)
      throws UnsupportedFormatException {
    return new JSONRepresentation(addKey(key, value));
  }

  public Representation with(String key, Object[] values) {
     return new JSONRepresentation(addKey(key, arrayFrom(values)));
  }

  public Representation with(String key, Representation[] representations)
      throws UnsupportedFormatException {
    return new JSONRepresentation(
      addKey(key, arrayFrom(representations))
    );
  }

  public String toString() {
    return json.toString();
  }

  private JSONObject copy() {
    return new JSONObject(this.json.toString());
  }

  private JSONObject addKey(String key, Object value) {
    return copy().put(key, value);
  }

  private JSONObject addKey(String key, Representation representation)
      throws UnsupportedFormatException {
    return copy().put(key, valueFrom(representation));
  }

  private JSONObject valueFrom(Representation representation)
      throws UnsupportedFormatException {
    try {
      return new JSONObject(
        representation.toString()
      );
    } catch (JSONException cause) {
      throw new UnsupportedFormatException(
        "Nested representation must be on JSON format.",
        cause
      );
    }
  }

  private JSONArray arrayFrom(Object[] values) {
    JSONArray array = new JSONArray();
    for (Object value : values) {
      array.put(value);
    }
    return array;
  }

  private JSONArray arrayFrom(Representation[] representations)
    throws UnsupportedFormatException {
    JSONArray array = new JSONArray();
    for (Representation representation : representations) {
      array.put(valueFrom(representation));
    }
    return array;
  }
}
