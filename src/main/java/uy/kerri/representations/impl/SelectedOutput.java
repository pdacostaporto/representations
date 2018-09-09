package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

public final class SelectedOutput implements Output {
    private final Output selected;

    public SelectedOutput(final Output output) {
        this.selected = output;
    }

    public SelectedOutput(final String field) {
        this(new SelectedFieldOutput(field));
    }

    public SelectedOutput(final String field, final Output nested) {
        this(new SelectedFieldOutput(field, nested));
    }

    public SelectedOutput(final Integer index) {
        this(new SelectedIndexOutput(index));
    }

    public SelectedOutput(final Integer index, final Output nested) {
        this(new SelectedIndexOutput(index, nested));
    }

    @Override
    public final String show() throws Exception {
        return this.selected.show();
    }

    @Override
    public final Output print(
        final String key, final String value
    ) throws Exception {
        return this.selected.print(key, value);
    }

    @Override
    public final Output print(
        final String key, final Integer value
    ) throws Exception {
        return this.selected.print(key, value);
    }

    @Override
    public final Output print(
        final String key, final Boolean value
    ) throws Exception {
        return this.selected.print(key, value);
    }

    @Override
    public final Output print(
        final String key, final Double value
    ) throws Exception {
        return this.selected.print(key, value);
    }

    @Override
    public final Output print(
        final String key, final Long value
    ) throws Exception {
        return this.selected.print(key, value);
    }

    @Override
    public final Output print(
        final String key, final Fields value
    ) throws Exception {
        return this.selected.print(key, value);
    }

    @Override
    public final Output print(
        final String key, final Values values
    ) throws Exception {
        return this.selected.print(key, values);
    }
}
