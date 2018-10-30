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
 * An {@link uy.kerri.representations.Output} that selects the value of a field.
 *
 * @since 2.0
 */
final class SelectedFieldOutput implements Output {
    /**
     * The field to be selected.
     */
    private String field;

    /**
     * The output where the selected field will be printed.
     */
    private Output nested;

    /**
     * Constructs an output that selects a field and prints it on the nested
     *  output.
     *
     * @param name The name of the field to be selected.
     * @param output The output to print the field on.
     */
    SelectedFieldOutput(final String name, final Output output) {
        this.field = name;
        this.nested = output;
    }

    /**
     * Constructs an output that selects the value of a field.
     *
     * @param name The name of the field to be selected.
     */
    SelectedFieldOutput(final String name) {
        this(
            name,
            new SelectedValueOutput(
                new ValueNotSelectedException(
                    String.format("The field '%s' wasn't selected yet.", name)
                )
            )
        );
    }

    @Override
    public String show() throws RepresentationsException {
        return this.nested.show();
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key,
        final Fields value
    ) throws RepresentationsException {
        return this.select(key, value.printTo(this.nested));
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.select(key, values.printTo(this.nested));
    }

    /**
     * Selects the printed output if the printed field matches the field to
     *  select.
     *
     * @param key The name of the printed field.
     * @param printed The output with the field printed on it.
     * @return The printed output if the field matches the field to select or
     *  this otherwise.
     * @throws RepresentationsException if something fails.
     */
    private Output select(
        final String key, final Output printed
    ) throws RepresentationsException {
        final Output selected;
        if (key.equals(this.field)) {
            selected = new SelectedFieldOutput(this.field, printed);
        } else {
            selected = this;
        }
        return selected;
    }
}
