package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Test;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

public final class MatchingValueTest implements Test {
    private final Value expected;
    private final Value actual;

    public MatchingValueTest(final Value some, final Value other) {
        this.expected = some;
        this.actual = other;
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(
            this.actual.printTo(
                this.expected.printTo(new PairMatchingOutput())
            ).show()
        );
    }
}
