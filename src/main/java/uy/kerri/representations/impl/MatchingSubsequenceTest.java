package uy.kerri.representations.impl;

import uy.kerri.representations.Test;
import uy.kerri.representations.Values;

public final class MatchingSubsequenceTest implements Test {
    private final Integer start;
    private final Values sequence;
    private final Values subsequence;

    public MatchingSubsequenceTest(
        final Integer start, final Values first, final Values second
    ) {
        this.start = start;
        this.sequence = first;
        this.subsequence = second;
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(
            this.subsequence.printTo(
                new SubsequenceMatchingOutput(this.start, this.sequence)
            ).show()
        );
    }
}
