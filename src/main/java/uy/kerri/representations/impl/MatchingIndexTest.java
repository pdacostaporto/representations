package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Test;
import uy.kerri.representations.Values;

public final class MatchingIndexTest implements Test {
    private final Output output;
    private final Values values;

    private MatchingIndexTest(final Output out, final Values actual) {
        this.output = out;
        this.values = actual;
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final Fields value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, value), actual);
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final Values values,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, values), actual);
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final String value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, value), actual);
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final Integer value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, value), actual);
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final Boolean value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, value), actual);
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final Long value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, value), actual);
    }

    public MatchingIndexTest(
        final Integer index,
        final String label,
        final Double value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, label, value), actual);
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(this.values.printTo(this.output).show());
    }
}
