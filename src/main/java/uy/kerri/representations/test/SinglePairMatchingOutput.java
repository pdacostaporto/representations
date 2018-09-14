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
 *  printed on it matches a given key-value pair with a single value as value.
 *
 * @since 2.0
 */
final class SinglePairMatchingOutput implements Output {
    /**
     * The label of the pair.
     */
    private final String label;

    /**
     * The value of the pair.
     */
    private final Object expected;

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
    private SinglePairMatchingOutput(
        final String key, final Object value, final Boolean status
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
    SinglePairMatchingOutput(final String key, final Object value) {
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
        return this.next(key, value);
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.next(key, value);
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.next(key, value);
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.next(key, value);
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.next(key, value);
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.next(key, value);
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.next(key, values);
    }

    /**
     * The next state for the output after a value is printed.
     *
     * @param key The printed label.
     * @param value The printed value.
     * @return An output updated to indicate if the new printed pair matched.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output next(
        final String key, final Object value
    ) throws RepresentationsException {
        return new SinglePairMatchingOutput(
            this.label,
            this.expected,
            key.equals(this.label) && value.equals(this.expected)
        );
    }
}
