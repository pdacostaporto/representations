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
import uy.kerri.representations.Value;

/**
 * A {@link uy.kerri.representations.test.Test} to verify that two values match.
 *
 * @since 2.0
 */
public final class MatchingValueTest implements Test {
    /**
     * The expected value.
     */
    private final Value expected;

    /**
     * The actual value.
     */
    private final Value actual;

    /**
     * Constructs a test to verify that to values match.
     *
     * @param some The expected value.
     * @param other The actual value.
     */
    public MatchingValueTest(final Value some, final Value other) {
        this.expected = some;
        this.actual = other;
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(
            this.actual.printTo(
                this.expected.printTo(new PairMatchingOutput())
            ).show()
        );
    }
}
