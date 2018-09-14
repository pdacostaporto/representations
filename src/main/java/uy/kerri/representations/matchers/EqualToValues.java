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
package uy.kerri.representations.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;
import uy.kerri.representations.fake.FakeOutput;
import uy.kerri.representations.test.MatchingValuesTest;

/**
 * Hamcrest matcher to match two equal sequences of values.
 *
 * @since 2.0
 */
final class EqualToValues extends TypeSafeMatcher<Values> {
    /**
     * The expected sequence of values.
     */
    private final Values expected;

    /**
     * Constructs a matcher to test if a sequence of values is equal to a
     *  expected sequence.
     *
     * @param expected The expected sequence.
     */
    EqualToValues(final Values expected) {
        super();
        this.expected = expected;
    }

    @Override
    public void describeTo(final Description description) {
        try {
            description.appendValue(
                this.expected.printTo(new FakeOutput()).show()
            );
        } catch (final RepresentationsException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    protected void describeMismatchSafely(
        final Values actual, final Description description
    ) {
        try {
            description.appendText("the actual was ").appendValue(
                actual.printTo(new FakeOutput()).show()
            );
        } catch (final RepresentationsException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    protected boolean matchesSafely(final Values actual) {
        try {
            return new MatchingValuesTest(this.expected, actual).passes();
        } catch (final RepresentationsException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
