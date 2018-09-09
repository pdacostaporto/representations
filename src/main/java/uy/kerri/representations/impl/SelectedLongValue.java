package uy.kerri.representations.impl;

import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.SelectedValue;

public final class SelectedLongValue implements SelectedValue<Long> {
    private final Representation origin;
    private final Output selection;

    public SelectedLongValue(
        final Representation representation,
        final Output output
    ) {
        this.origin = representation;
        this.selection = output;
    }

    public Long value() throws Exception {
        return Long.valueOf(this.origin.printTo(selection).show());
    }
}
