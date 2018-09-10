package uy.kerri.representations.impl;

import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

public final class SelectedValuesFromIndex implements Values {
    private final Integer index;
    private final Values container;

    public SelectedValuesFromIndex(final Integer idx, final Values values) {
        this.index = idx;
        this.container = values;
    }

    @Override
    public Output printTo(final Output output) throws Exception {
        return this.container.printTo(new SelectedOutput(this.index, output));
    }
}
