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
package uy.kerri.representations.test;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if the label of the
 *  last printed label-value pair matches a given label.
 *
 * @since 2.0
 */
final class SelectedLabelMatchingOutput implements Output {
    /**
     * The expected label.
     */
    private final String expected;

    /**
     * Whether the last printed label matched or not.
     */
    private final Boolean matched;

    /**
     * Constructs an output that shows if the label of the last printed
     *  label-value pair matches a given label.
     *
     * @param label The expected label.
     * @param status Whether the last printed label matched or not.
     */
    private SelectedLabelMatchingOutput(
        final String label, final Boolean status
    ) {
        this.expected = label;
        this.matched = status;
    }

    /**
     * Constructs an output that shows if the label of the last printed
     *  label-value pair matches a given label, assuming nothing was printed
     *  yet.
     *
     * @param label The expected label.
     */
    SelectedLabelMatchingOutput(final String label) {
        this(label, false);
    }

    @Override
    public String show() {
        return this.matched.toString();
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.match(key);
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.match(key);
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.match(key);
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.match(key);
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.match(key);
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.match(key);
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.match(key);
    }

    /**
     * Determines whether a currently printed label matches the expected label.
     *
     * @param actual The printed label.
     * @return An output indicating if the label matched.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(final String actual) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(
            this.expected, actual.equals(this.expected)
        );
    }
}
