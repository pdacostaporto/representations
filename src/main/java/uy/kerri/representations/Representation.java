package uy.kerri.representations;

import uy.kerri.representations.Representable;

public interface Representation {
  Representation with(String key, Object value);

  Representation with(String key, Representable value);

  Representation with(String key, Object[] objects);

  Representation with(String key, Representable[] representables);
}
