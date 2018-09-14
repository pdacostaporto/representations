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

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Values;

/**
 * Tests for {@link uy.kerri.representations.test.MatchingValuesInAnyOrderTest}.
 *
 * @since 2.0
 */
public final class MatchingValuesInAnyOrderTestTest {
    /**
     * MatchingValuesInAnyOrderTest matches two sequences if they contain the
     *  same values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesTwoEqualSequences() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", true);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, first, third);
        MatcherAssert.assertThat(
            "Two equal sequences of values didn't match.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingValuesInAnyOrderTest does not pass if the second sequence misses
     *  some value from the first.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchAShorterSequence() throws Exception {
        final LabelledValue first = new LabelledValue("1st field", "1");
        final LabelledValue second = new LabelledValue("2nd field", 2);
        final LabelledValue third = new LabelledValue("3rd field", "3");
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, first);
        MatcherAssert.assertThat(
            "A sequence of values matched to a shorter sequence.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingValuesInAnyOrderTest does not pass if the second sequence has
     *  additional values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchALongerSequence() throws Exception {
        final LabelledValue first = new LabelledValue("1st", "1st value");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue("3rd", true);
        final LabelledValue fourth = new LabelledValue("4th", "4th value");
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, first, third, fourth);
        MatcherAssert.assertThat(
            "A sequence of values matched to a longer sequence.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingValuesInAnyOrderTest does not pass if some value has a different
     *  label.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchIfALabelDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 2);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(alternate, first, third);
        MatcherAssert.assertThat(
            "A sequences matches another with different labels.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingValuesInAnyOrderTest does not pass if the sequences have
     *  different duplicate values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchIfADifferentValueIsRepeated() throws Exception {
        final LabelledValue first = new LabelledValue("1stF", "1stV");
        final LabelledValue second = new LabelledValue("2ndF", 2);
        final LabelledValue third = new LabelledValue("3rdF", true);
        final Values some = new ArrayOfValues(first, second, second, third);
        final Values other = new ArrayOfValues(first, first, third, second);
        MatcherAssert.assertThat(
            "A sequence matches another with different repeated values.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingValuesInAnyOrderTest passes if the sequences have the same
     *  duplicate values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesIfTheSameValueIsRepeated() throws Exception {
        final LabelledValue first = new LabelledValue("1F", "1V");
        final LabelledValue second = new LabelledValue("2F", 2);
        final LabelledValue third = new LabelledValue("3F", true);
        final Values some = new ArrayOfValues(first, second, first, third);
        final Values other = new ArrayOfValues(first, first, third, second);
        MatcherAssert.assertThat(
            "A sequence didn't match another with the same repeated values.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            CoreMatchers.is(true)
        );
    }
}
