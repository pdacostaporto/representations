package uy.kerri.representations.impl;

import java.util.List;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;

public final class FilteredFields implements Fields {
    private final List<String> filtered;
    private final Fields origin;

    public FilteredFields(final List<String> names, final Fields fields) {
        this.filtered = names;
        this.origin = fields;
    }

    @Override
    public Output printTo(final Output output) throws Exception {
        return this.origin.printTo(new FilteringOutput(this.filtered, output));
    }
}
