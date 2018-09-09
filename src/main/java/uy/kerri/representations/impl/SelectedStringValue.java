package uy.kerri.representations.impl;

import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.SelectedValue;

public final class SelectedStringValue
implements SelectedValue<java.lang.String> {
    private final Representation origin;
    private final Output selection;

    public SelectedStringValue(
        final Representation representation,
        final Output output
    ) {
        this.origin = representation;
        this.selection = output;
    }

    public java.lang.String value() throws Exception {
        return this.origin.printTo(selection).show();
    }
}
