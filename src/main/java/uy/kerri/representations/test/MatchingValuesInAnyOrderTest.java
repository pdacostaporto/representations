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

import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * A {@link uy.kerri.representations.test.Test} that verifies that two sequences
 *  of values match disregarding the order of their values.
 *
 * @since 2.0
 */
public final class MatchingValuesInAnyOrderTest implements Test {
    /**
     * The test that verifies that one sequence is contained in the other.
     */
    private final Test contained;

    /**
     * The test that verifies that both sequences have the same length.
     */
    private final Test length;

    /**
     * Constructs a test that verifies that two sequences of values match
     *  disregarding the order of their values.
     *
     * @param expected The expected sequence.
     * @param actual The actual sequence.
     */
    public MatchingValuesInAnyOrderTest(
        final Values expected, final Values actual
    ) {
        this.contained = new ContainedValuesTest(expected, actual);
        this.length = new MatchingCountTest(expected, actual);
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return this.contained.passes() && this.length.passes();
    }
}
