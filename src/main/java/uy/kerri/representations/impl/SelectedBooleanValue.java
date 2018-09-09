package uy.kerri.representations.impl;

import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.SelectedValue;

public final class SelectedBooleanValue implements SelectedValue<Boolean> {
    private final Representation origin;
    private final Output selection;

    public SelectedBooleanValue(
        final Representation representation,
        final Output output
    ) {
        this.origin = representation;
        this.selection = output;
    }

    public Boolean value() throws Exception {
        return Boolean.valueOf(this.origin.printTo(selection).show());
    }
}
