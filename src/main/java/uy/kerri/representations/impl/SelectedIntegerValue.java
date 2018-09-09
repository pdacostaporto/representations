package uy.kerri.representations.impl;

import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.SelectedValue;

public final class SelectedIntegerValue implements SelectedValue<Integer> {
    private final Representation origin;
    private final Output selection;

    public SelectedIntegerValue(
        final Representation representation,
        final Output output
    ) {
        this.origin = representation;
        this.selection = output;
    }

    public Integer value() throws Exception {
        return Integer.valueOf(this.origin.printTo(selection).show());
    }
}
