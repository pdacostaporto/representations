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

import org.hamcrest.Matcher;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Values;

/**
 * Hamcrest matchers for testing representations.
 *
 * @since 2.0
 */
@SuppressWarnings("PMD.ProhibitPublicStaticMethods")
public final class RepresentationMatchers {
    /**
     * Private constructor to prevent instantiation.
     */
    private RepresentationMatchers() {
    }

    /**
     * Matcher to test the equality to a sequence of values.
     *
     * @param expected The expected sequence of values.
     * @return A matcher to test the equality to the expected sequence.
     */
    public static Matcher<Values> equalTo(final Values expected) {
        return new EqualToValues(expected);
    }

    /**
     * Matcher to test the equality to a sequence of values disregarding the
     *  order of the values.
     *
     * @param expected The expected sequence of values.
     * @return A matcher to test the equality to the expected sequence
     *  disregarding the order of the values.
     */
    public static Matcher<Values> equalToInAnyOrder(
        final Values expected
    ) {
        return new EqualToValuesInAnyOrder(expected);
    }

    /**
     * Matcher to test that a group of values is contained on another.
     *
     * @param expected The containing group of values.
     * @return A matcher to test that a group of values is contained on the
     *  expected group of values.
     */
    public static Matcher<Values> contains(
        final Values expected
    ) {
        return new ContainsValues(expected);
    }

    /**
     * Matcher to test the equality to a set of fields.
     *
     * @param expected The expected set of fields.
     * @return A matcher to test the equality to the expected set of fields.
     */
    public static Matcher<Fields> equalTo(final Fields expected) {
        return new EqualToFields(expected);
    }
}
