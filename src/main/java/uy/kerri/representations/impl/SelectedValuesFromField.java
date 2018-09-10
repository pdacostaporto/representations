package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

public final class SelectedValuesFromField implements Values {
    private final String field;
    private final Fields container;

    public SelectedValuesFromField(final String name, final Fields fields) {
        this.field = name;
        this.container = fields;
    }

    @Override
    public Output printTo(final Output output) throws Exception {
        return this.container.printTo(new SelectedOutput(this.field, output));
    }
}
