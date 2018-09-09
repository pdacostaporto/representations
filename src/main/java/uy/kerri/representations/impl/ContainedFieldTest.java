package uy.kerri.representations.impl;

import uy.kerri.representations.Field;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Test;
import uy.kerri.representations.Values;

public final class ContainedFieldTest implements Test {
    private final Output output;
    private final Fields fields;

    private ContainedFieldTest(final Output out, final Fields actual) {
        this.output = out;
        this.fields = actual;
    }

    public ContainedFieldTest(final Field field, final Fields actual) {
        this(new FieldMatchingOutput(field), actual);
    }

    public Boolean passes() throws Exception {
        return Boolean.valueOf(this.fields.printTo(this.output).show());
    }
}
