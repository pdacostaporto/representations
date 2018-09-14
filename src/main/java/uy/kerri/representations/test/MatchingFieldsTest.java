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
import uy.kerri.representations.RepresentationsException;

/**
 * A {@link uy.kerri.representations.test.Test} to verify that two sets of
 *  fields match.
 *
 * @since 2.0
 */
public final class MatchingFieldsTest implements Test {
    /**
     * The expected fields.
     */
    private final Fields expected;

    /**
     * The actual fields.
     */
    private final Fields actual;

    /**
     * Constructs a test to verify that two sets of fields match.
     *
     * @param first The expected set of fields.
     * @param second The actual set of fields.
     */
    public MatchingFieldsTest(final Fields first, final Fields second) {
        this.expected = first;
        this.actual = second;
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(
            this.expected.printTo(new FieldsMatchingOutput(this.actual)).show()
        ) && Boolean.valueOf(
            this.actual.printTo(new FieldsMatchingOutput(this.expected)).show()
        );
    }
}
