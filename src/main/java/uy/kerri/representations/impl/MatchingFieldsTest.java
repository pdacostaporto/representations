package uy.kerri.representations.impl;

import uy.kerri.representations.Test;
import uy.kerri.representations.Fields;

public final class MatchingFieldsTest implements Test {
    private final Fields some;
    private final Fields other;

    public MatchingFieldsTest(final Fields first, final Fields second) {
        this.some = first;
        this.other = second;
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(
            this.some.printTo(new FieldsMatchingOutput(this.other)).show()
        ) && Boolean.valueOf(
            this.other.printTo(new FieldsMatchingOutput(this.some)).show()
        );
    }
}
