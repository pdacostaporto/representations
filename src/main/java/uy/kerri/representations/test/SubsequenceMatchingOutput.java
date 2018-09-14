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
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if the values printed
 *  on it match a subsequence inside another sequence of values.
 *
 * @since 2.0
 */
final class SubsequenceMatchingOutput implements Output {
    /**
     * The index in the containing sequence corresponding to the next value to
     *  be printed.
     */
    private final Integer current;

    /**
     * The sequence.
     */
    private final Values actual;

    /**
     * Whether the values printed so far match a subsequence.
     */
    private final Boolean matched;

    /**
     * Constructs an output that shows if the values printed on it match a
     *  subsequence inside another sequence of values, considering whether the
     *  values printed so far matched a subsequence or not.
     *
     * @param index The index in the sequence correspondent to the next value to
     *  be printed.
     * @param values The sequence.
     * @param status Whether the values printed so far matched a subsequence or
     *  not.
     */
    private SubsequenceMatchingOutput(
        final Integer index, final Values values, final Boolean status
    ) {
        this.current = index;
        this.actual = values;
        this.matched = status;
    }

    /**
     * Constructs an output that shows if the values printed on it match a
     *  subsequence inside another sequence of values, positioned at the start
     *  of the subsequence to be matched.
     *
     * @param index The index in the sequence of the start of the subsequence to
     *  be matched.
     * @param values The sequence.
     */
    SubsequenceMatchingOutput(final Integer index, final Values values) {
        this(index, values, true);
    }

    @Override
    public String show() {
        return this.matched.toString();
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
     * Determines whether the printed values, including the currently printed
     *  value, match the expected subsequence.
     *
     * @param value The currently printed value.
     * @return An output updated to indicate if the printed values match a
     *  subsequence.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(final Value value) throws RepresentationsException {
        return new SubsequenceMatchingOutput(
            this.current + 1,
            this.actual,
            this.matched && Boolean.valueOf(
                this.actual
                .printTo(new IndexMatchingOutput(this.current, value)).show()
            )
        );
    }
}
