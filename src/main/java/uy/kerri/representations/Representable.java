package uy.kerri.representations;

import uy.kerri.representations.Representation;

public interface Representable {
  Representation summary(Representation representation);

  Representation representation(Representation representation);
}
