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
package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;
import uy.kerri.representations.exception.IndexNotFoundException;

/**
 * {@link uy.kerri.representations.Output} to selects the n-th printed value in
 *  a sequence of values.
 *
 * @since 1.0
 */
public class SelectedIndexOutput implements Output {
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
     *  sequence on the output.
     *
     * @param idx The index of the value to be selected.
     * @param output The output where the selected value will be printed.
     */
    public SelectedIndexOutput(final Integer idx, final Output output) {
        this.index = idx;
        this.nested = output;
    }

    /**
     * Constructs an output that selects the value of a.
     *
     * @param idx The index of the value to be selected.
     */
    public SelectedIndexOutput(final Integer idx) {
        this(idx, new SelectedValueOutput());
    }

    @Override
    public final String show() throws IndexNotFoundException {
        throw new IndexNotFoundException("The index is not present.");
    }

    @Override
    public final Output print(
        final String key, final String value
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = this.nested.print(key, value);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }

    @Override
    public final Output print(
        final String key, final Integer value
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = this.nested.print(key, value);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }

    @Override
    public final Output print(
        final String key, final Boolean value
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = this.nested.print(key, value);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }

    @Override
    public final Output print(
        final String key, final Double value
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = this.nested.print(key, value);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }

    @Override
    public final Output print(
        final String key, final Long value
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = this.nested.print(key, value);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }

    @Override
    public final Output print(
        final String key, final Fields value
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = value.printTo(this.nested);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }

    @Override
    public final Output print(
        final String key, final Values values
    ) throws Exception {
        final Output output;
        if (this.index.equals(1)) {
            output = values.printTo(this.nested);
        } else {
            output = new SelectedIndexOutput(this.index - 1, this.nested);
        }
        return output;
    }
}
