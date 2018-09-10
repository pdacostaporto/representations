package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

public final class SelectedFields implements Fields {
    private final Fields selected;

    private SelectedFields(final Fields values) {
        this.selected = values;
    }

    public SelectedFields(final String field, final Fields fields) {
        this(new SelectedFieldsFromField(field, fields));
    }

    public SelectedFields(final Integer index, final Values values) {
        this(new SelectedFieldsFromIndex(index, values));
    }

    @Override
    public Output printTo(final Output output) throws Exception {
        return this.selected.printTo(output);
    }
}
