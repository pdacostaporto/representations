package uy.kerri.representations.impl;

import uy.kerri.representations.Field;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Test;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

public final class MatchingFieldNameTest implements Test {
    private final Field expected;
    private final Field actual;

    public MatchingFieldNameTest(final Field some, final Field other) {
        this.expected = some;
        this.actual = other;
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(
            this.actual.printTo(
                this.expected.printTo(new LabelMatchingOutput())
            ).show()
        );
    }
}
