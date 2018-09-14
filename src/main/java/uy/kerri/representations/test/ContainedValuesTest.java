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
 * A {@link uy.kerri.representations.test.Test} to verify that the values from a
 *  sequence are contained into another.
 *
 * @since 2.0
 */
public final class ContainedValuesTest implements Test {
    /**
     * The sequence that should contain the values.
     */
    private final Values container;

    /**
     * The values to be matched.
     */
    private final Values contained;

    /**
     * Constructs a test to verify that the values from a sequence are contained
     *  into another.
     *
     * @param first The sequence that should contain the values.
     * @param second The values to be matched.
     */
    public ContainedValuesTest(final Values first, final Values second) {
        this.container = first;
        this.contained = second;
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(
            this.contained.printTo(new ContainedValuesOutput(this.container))
            .show()
        );
    }
}
