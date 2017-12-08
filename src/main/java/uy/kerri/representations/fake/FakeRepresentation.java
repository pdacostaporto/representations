package uy.kerri.representations.fake;

import uy.kerri.representations.exception.UnsupportedFormatException;
import uy.kerri.representations.Representation;

public class FakeRepresentation implements Representation {
  private String representation;

  public FakeRepresentation(String representation) {
    this.representation = representation;
  }

  public Representation with(String key, Object value) {
    return this;
  }

  public Representation with(String key, Representation value)
      throws UnsupportedFormatException {
    return this;
  }

  public Representation with(String key, Object[] values) {
     return this;
  }

  public Representation with(String key, Representation[] representations)
      throws UnsupportedFormatException {
    return this;
  }

  public String toString() {
    return this.representation;
  }
}
