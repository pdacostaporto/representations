package uy.kerri.representations.impl;

import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.SelectedValue;

public final class SelectedDoubleValue implements SelectedValue<Double> {
    private final Representation origin;
    private final Output selection;

    public SelectedDoubleValue(
        final Representation representation,
        final Output output
    ) {
        this.origin = representation;
        this.selection = output;
    }

    public Double value() throws Exception {
        return Double.valueOf(this.origin.printTo(selection).show());
    }
}
