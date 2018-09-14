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
import uy.kerri.representations.FixedOutput;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if the n-th value in a
 *  sequence of values matches a given value.
 *
 * @since 2.0
 */
final class IndexMatchingOutput implements Output {
    /**
     * The index where the value is to be matched.
     */
    private final Integer index;

    /**
     * The value to be matched.
     */
    private final Value expected;

    /**
     * The index of the next value to be printed.
     */
    private final Integer current;

    /**
     * Constructs an output that shows if the value at a given place in a
     *  sequence of values matches a given value, considering the position
     *  correspondent to the next value to be printed.
     *
     * @param idx The index where the value is to be matched.
     * @param value The value to be matched.
     * @param position The index of the next value to be printed.
     */
    IndexMatchingOutput(
        final Integer idx, final Value value, final Integer position
    ) {
        this.index = idx;
        this.expected = value;
        this.current = position;
    }

    /**
     * Constructs an output that shows if the value at a given place in a
     *  sequence of values matches a given value.
     *
     * @param idx The index where the value is to be matched.
     * @param value The value to be matched.
     */
    IndexMatchingOutput(final Integer idx, final Value value) {
        this(idx, value, 1);
    }

    @Override
    public String show() {
        return "false";
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, values));
    }

    /**
     * Matches the value.
     *
     * @param value The value to be matched.
     * @return An output updated to whether the value matched or not.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(final Value value) throws RepresentationsException {
        final Output selected;
        if (this.current.equals(this.index)) {
            selected = new FixedOutput(
                new MatchingValueTest(this.expected, value).passes()
            );
        } else {
            selected = new IndexMatchingOutput(
                this.index, this.expected, this.current + 1
            );
        }
        return selected;
    }
}
