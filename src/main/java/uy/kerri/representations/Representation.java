package uy.kerri.representations;

import uy.kerri.representations.exception.UnsupportedFormatException;

public interface Representation {
  Representation with(String key, Object value);

  Representation with(String key, Representation value)
      throws UnsupportedFormatException;

  Representation with(String key, Object[] objects);

  Representation with(String key, Representation[] values)
      throws UnsupportedFormatException;
}
