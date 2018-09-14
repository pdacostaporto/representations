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

import java.util.ArrayList;
import java.util.List;
import uy.kerri.representations.Fields;
import uy.kerri.representations.FixedOutput;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows the position of the
 *  first value printed on it that matches a given value.
 *
 * @since 2.0
 */
final class ContainedValueOutput implements Output {
    /**
     * The index of the current value to be printed.
     */
    private final Integer current;

    /**
     * The value to be matched.
     */
    private final Value actual;

    /**
     * Indexes to ignore.
     */
    private final List<Integer> already;

    /**
     * Constructs an output that shows the position of the first value printed
     *  on it that matches a given value, considering a current position in the
     *  sequence and a list of indexes to be excluded from matching.
     *
     * @param index The current index in the sequence.
     * @param value The value to be matched.
     * @param exclude A list of indexes to be excluded from matching.
     */
    ContainedValueOutput(
        final Integer index,
        final Value value,
        final List<Integer> exclude
    ) {
        this.current = index;
        this.actual = value;
        this.already = exclude;
    }

    /**
     * Constructs an output that shows if any of the values printed on it
     *  matches a given value, considering a list of indexes to be excluded from
     *  matching and assuming that it is the start of the sequence.
     *
     * @param value The value to be matched.
     * @param exclude A list of indexes to be excluded from matching.
     */
    ContainedValueOutput(final Value value, final List<Integer> exclude) {
        this(1, value, exclude);
    }

    /**
     * Constructs an output that shows if any of the values printed on it
     *  matches a given value.
     *
     * @param value The value to be matched.
     */
    ContainedValueOutput(final Value value) {
        this(value, new ArrayList<Integer>(0));
    }

    @Override
    public String show() {
        return "0";
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
     * Matches the printed value if it corresponds.
     *
     * @param value The last printed value.
     * @return An output indicating whether the value was matched or not.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(final Value value) throws RepresentationsException {
        final Output selected;
        if (
            !this.already.contains(this.current)
                && new MatchingValueTest(value, this.actual).passes()
        ) {
            selected = new FixedOutput(this.current);
        } else {
            selected = new ContainedValueOutput(
                this.current + 1, this.actual, this.already
            );
        }
        return selected;
    }
}
