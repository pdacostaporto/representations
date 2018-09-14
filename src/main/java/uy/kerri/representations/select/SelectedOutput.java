/*
 * MIT License
 *
 * Copyright (c) 2018 Pablo Da Costa Porto
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package uy.kerri.representations.select;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that selects some encapsulated
 *  value.
 *
 * @since 2.0
 */
public final class SelectedOutput implements Output {
    /**
     * The output that selects the value.
     */
    private final Output selected;

    /**
     * Constructs an output that selects a value.
     *
     * @param output The output that selects the value.
     */
    private SelectedOutput(final Output output) {
        this.selected = output;
    }

    /**
     * Constructs an output that selects the value of a field in a group of
     *  fields.
     *
     * @param field The name of the field to be selected.
     */
    public SelectedOutput(final String field) {
        this(new SelectedFieldOutput(field));
    }

    /**
     * Constructs an output that selects the value of a field in a group of
     *  fields and prints it on a nested output.
     *
     * @param field The name of the field to be selected.
     * @param nested The output where to print the selected field.
     */
    public SelectedOutput(final String field, final Output nested) {
        this(new SelectedFieldOutput(field, nested));
    }

    /**
     * Constructs an output that selects the value of an index in a sequence of
     *  values.
     *
     * @param index The index of the value to be selected.
     */
    public SelectedOutput(final Integer index) {
        this(new SelectedIndexOutput(index));
    }

    /**
     * Constructs an output that selects the value of an index in a sequence of
     *  values and prints it on a nested output.
     *
     * @param index The index of the value to be selected.
     * @param nested The output where to print the selected value.
     */
    public SelectedOutput(final Integer index, final Output nested) {
        this(new SelectedIndexOutput(index, nested));
    }

    @Override
    public String show() throws RepresentationsException {
        return this.selected.show();
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.selected.print(key, value);
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.selected.print(key, value);
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.selected.print(key, value);
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.selected.print(key, value);
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.selected.print(key, value);
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.selected.print(key, value);
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.selected.print(key, values);
    }
}
