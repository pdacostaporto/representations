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
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if values printed on it
 *  are contained in a given sequence of values.
 *
 * @since 2.0
 */
final class ContainedValuesOutput implements Output {
    /**
     * The sequence of values to be matched.
     */
    private final Values actual;

    /**
     * The indexes of the already matched values in the containing sequence,
     *  with 0 indicating that a value didn't match.
     */
    private final List<Integer> matched;

    /**
     * Constructs an output that shows if values printed on it are contained in
     *  a given sequence of values, considering a list of indexes of given
     *  sequence that have already been matched, with 0 indicating that a
     *  printed value didn't match.
     *
     * @param values The sequence of values to be matched.
     * @param already A list of the indexes already matched.
     */
    private ContainedValuesOutput(
        final Values values, final List<Integer> already
    ) {
        this.actual = values;
        this.matched = already;
    }

    /**
     * Constructs an output that shows if values printed on it are contained in
     *  a given sequence of values.
     *
     * @param values The sequence of values to be matched.
     */
    ContainedValuesOutput(final Values values) {
        this(values, new ArrayList<Integer>(0));
    }

    @Override
    public String show() {
        return String.format("%b", !this.matched.contains(0));
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
     * Matches the printed value.
     *
     * @param value The recently printed value.
     * @return An output updated to whether the value was contained in the
     *  sequence or not.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(final Value value) throws RepresentationsException {
        final List<Integer> exclude = new ArrayList<>(this.matched);
        exclude.add(
            Integer.valueOf(
                this.actual.printTo(new ContainedValueOutput(value, exclude))
                .show()
            )
        );
        return new ContainedValuesOutput(this.actual, exclude);
    }
}
