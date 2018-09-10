package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

public final class SelectedValues implements Values {
    private final Values selected;

    private SelectedValues(final Values values) {
        this.selected = values;
    }

    public SelectedValues(final String field, final Fields fields) {
        this(new SelectedValuesFromField(field, fields));
    }

    public SelectedValues(final Integer index, final Values values) {
        this(new SelectedValuesFromIndex(index, values));
    }

    @Override
    public Output printTo(final Output output) throws Exception {
        return this.selected.printTo(output);
    }
}
