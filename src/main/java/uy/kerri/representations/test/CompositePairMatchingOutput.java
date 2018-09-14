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
 * An {@link uy.kerri.representations.Output} that shows if the last key-value
 *  printed on it matches a given key-value pair with a composite value as
 *  value.
 *
 * @since 2.0
 */
final class CompositePairMatchingOutput implements Output {
    /**
     * The label of the pair.
     */
    private final String label;

    /**
     * The value of the pair.
     */
    private final Fields expected;

    /**
     * Whether the last printed value matched or not.
     */
    private final Boolean matched;

    /**
     * Constructs an output that shows if the last label-value printed on it
     *  matches a given label-value pair with a single value as value.
     *
     * @param key The label.
     * @param value The single value to match.
     * @param status Whether the last printed pair matched or not.
     */
    private CompositePairMatchingOutput(
        final String key, final Fields value, final Boolean status
    ) {
        this.label = key;
        this.expected = value;
        this.matched = status;
    }

    /**
     * Constructs an output that shows if the last label-value printed on it
     *  matches a given label-value pair with a single value as value.
     *
     * @param key The label.
     * @param value The single value to match.
     */
    CompositePairMatchingOutput(final String key, final Fields value) {
        this(key, value, false);
    }

    @Override
    public String show() {
        return this.matched.toString();
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.next(false);
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.next(false);
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.next(false);
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.next(false);
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.next(false);
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.next(
            key.equals(this.label)
            && new MatchingFieldsTest(this.expected, value).passes()
        );
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.next(false);
    }

    /**
     * The next state for the output after a value is printed.
     *
     * @param matches Whether the printed pair matched or not.
     * @return An output updated to indicate if the new printed pair matched.
     */
    private Output next(final Boolean matches) {
        return new CompositePairMatchingOutput(
            this.label, this.expected, matches
        );
    }
}
