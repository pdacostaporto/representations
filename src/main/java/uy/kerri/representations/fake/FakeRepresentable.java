package uy.kerri.representations.fake;

import uy.kerri.representations.Representable;
import uy.kerri.representations.Representation;

public class FakeRepresentable implements Representable {
  private String summary;

  private String representation;

  private String summary_key = "summary";

  private String representation_key = "representation";

  public FakeRepresentable(String summary,
                           String representation) {
    this.summary = summary;
    this.representation = representation;
  }

  public Representation summary(Representation representation) {
    return representation.with("summary", this.summary);
  }

  public Representation representation(Representation representation) {
    return representation.with("representation", this.representation);
  }

  public String summaryKey() {
    return this.summary_key;
  }

  public String summaryString() {
    return this.summary;
  }

  public String representationKey() {
    return this.representation_key;
  }

  public String representationString() {
    return this.representation;
  }
}
