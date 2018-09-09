package uy.kerri.representations.impl;

import uy.kerri.representations.Test;
import uy.kerri.representations.Representation;

public final class MatchingCountTest implements Test {
    private final Representation some;
    private final Representation other;

    public MatchingCountTest(
        final Representation first, final Representation second
    ) {
        this.some = first;
        this.other = second;
    }

    public Boolean passes() throws Exception {
        return Integer.valueOf(this.some.printTo(new CountingOutput()).show())
        .equals(
            Integer.valueOf(this.other.printTo(new CountingOutput()).show())
        );
    }
}
