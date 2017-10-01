package uy.kerri.representations.impl;

import uy.kerri.representations.Representation;
import uy.kerri.representations.Representable;

import org.json.*;

public class JSONRepresentation implements Representation {
  private JSONObject json;

  public JSONRepresentation() {
    this(new JSONObject());
  }

  public Representation with(String key, Object value) {
    return new JSONRepresentation(addKey(key, value));
  }

  public Representation with(String key, Representable value) {
    return new JSONRepresentation(addKey(key, value));
  }

  public Representation with(String key, Object[] values) {
     return new JSONRepresentation(addKey(key, arrayFrom(values)));
  }

  public Representation with(String key, Representable[] representables) {
    return new JSONRepresentation(
      addKey(key, arrayFrom(representables))
    );
  }

  public String toString() {
    return json.toString();
  }

  private JSONRepresentation(JSONObject json) {
    this.json = json;
  }

  private JSONObject copy() {
    return new JSONObject(this.json.toString());
  }

  private JSONObject addKey(String key, Object value) {
    return copy().put(key, value);
  }

  private JSONObject addKey(String key, Representable representable) {
    return copy().put(key, valueFrom(representable));
  }

  private JSONObject valueFrom(Representable representable) {
    return new JSONObject(
      representable.summary(new JSONRepresentation()).toString()
    );
  }

  private JSONArray arrayFrom(Object[] values) {
    JSONArray array = new JSONArray();
    for (Object value : values) {
      array.put(value);
    }
    return array;
  }

  private JSONArray arrayFrom(Representable[] representables) {
    JSONArray array = new JSONArray();
    for (Representable representable : representables) {
      array.put(valueFrom(representable));
    }
    return array;
  }
}
