package uy.kerri.representations.impl;

import uy.kerri.representations.Test;
import uy.kerri.representations.Values;

public final class ContainedValuesTest implements Test {
    private final Values container;
    private final Values contained;

    public ContainedValuesTest(final Values first, final Values second) {
        this.container = first;
        this.contained = second;
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(
            this.contained.printTo(new ContainedValuesOutput(this.container))
            .show()
        );
    }
}
