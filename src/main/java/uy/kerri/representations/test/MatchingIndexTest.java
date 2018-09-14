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

import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * A {@link uy.kerri.representations.test.Test} to verify that contains a given
 *  value in a given place.
 *
 * @since 2.0
 */
public final class MatchingIndexTest implements Test {
    /**
     * The output that matches the sequence.
     */
    private final Output output;

    /**
     * The sequence of values.
     */
    private final Values values;

    /**
     * Constructs a test to verify that a sequence of values matches to an
     *  output.
     *
     * @param out The output that matches the sequence.
     * @param actual The sequence of values.
     */
    private MatchingIndexTest(final Output out, final Values actual) {
        this.output = out;
        this.values = actual;
    }

    /**
     * Constructs a test to verify that a sequence of values contains a given
     *  value in a given place.
     *
     * @param index The place in the sequence where the value must match.
     * @param value The value to match.
     * @param actual The sequence of values.
     */
    public MatchingIndexTest(
        final Integer index,
        final Value value,
        final Values actual
    ) {
        this(new IndexMatchingOutput(index, value), actual);
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(this.values.printTo(this.output).show());
    }
}
