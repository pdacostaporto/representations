package uy.kerri.representations.impl;

import uy.kerri.representations.Test;
import uy.kerri.representations.Values;

public final class MatchingValuesInAnyOrderTest implements Test {
    private final Test contained;
    private final Test length;

    public MatchingValuesInAnyOrderTest(final Values some, final Values other) {
        this.contained = new ContainedValuesTest(some, other);
        this.length = new MatchingCountTest(some, other);
    }

    public Boolean passes() throws Exception {
        return this.contained.passes() && this.length.passes();
    }
}
