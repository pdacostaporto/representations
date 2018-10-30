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
 * An {@link uy.kerri.representations.Output} to select the n-th printed value
 *  in a sequence of values.
 *
 * @since 2.0
 */
final class SelectedIndexOutput implements Output {
    /**
     * Place of the selected value in the sequence.
     */
    private Integer index;

    /**
     * The output where the value of the selected index will be printed.
     */
    private Output nested;

    /**
     * Constructs an output that prints the value of the given index in the
     *  sequence on the nested output.
     *
     * @param idx The index of the value to be selected.
     * @param output The output where the selected value will be printed.
     */
    SelectedIndexOutput(final Integer idx, final Output output) {
        this.index = idx;
        this.nested = output;
    }

    /**
     * Constructs an output that selects the value of the given index.
     *
     * @param idx The index of the value to be selected.
     */
    SelectedIndexOutput(final Integer idx) {
        this(
            idx,
            new SelectedValueOutput(
                new ValueNotSelectedException(
                    String.format("The index %d wasn't selected yet.", idx)
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
        return this.select(this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.select(this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.select(this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.select(this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.select(this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.select(value.printTo(this.nested));
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.select(values.printTo(this.nested));
    }

    /**
     * Selects the printed output if the actual index corresponds to the index
     *  to be selected.
     *
     * @param printed The output with the value of the index printed on it.
     * @return The printed output if the actual index corresponds to the index
     *  to select or an output to print the next index otherwise.
     * @throws RepresentationsException if something fails.
     */
    private Output select(
        final Output printed
    ) throws RepresentationsException {
        final Output selected;
        if (this.index.equals(1)) {
            selected = new SelectedIndexOutput(this.index - 1, printed);
        } else if (this.index > 1) {
            selected = new SelectedIndexOutput(this.index - 1, this.nested);
        } else {
            selected = this;
        }
        return selected;
    }
}
