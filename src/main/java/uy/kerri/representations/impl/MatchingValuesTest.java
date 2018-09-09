package uy.kerri.representations.impl;

import uy.kerri.representations.Test;
import uy.kerri.representations.Values;

public final class MatchingValuesTest implements Test {
    private final Test subsequence;
    private final Test length;

    public MatchingValuesTest(final Values some, final Values other) {
        this.subsequence = new MatchingSubsequenceTest(1, some, other);
        this.length = new MatchingCountTest(some, other);
    }

    public Boolean passes() throws Exception {
        return this.subsequence.passes() && this.length.passes();
    }
}
