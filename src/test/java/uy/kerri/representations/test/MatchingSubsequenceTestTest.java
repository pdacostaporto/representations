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
 * Tests for {@link uy.kerri.representations.test.MatchingSubsequenceTest}.
 *
 * @since 2.0
 */
public final class MatchingSubsequenceTestTest {
    /**
     * MatchingSubsequenceTest passes if the second sequence if a subsequence of
     *  the first starting at a certain position.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void passesIfASequenceIsContainedOnAnother() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", 1L);
        final LabelledValue fourth = new LabelledValue("fourth", false);
        final Values some = new ArrayOfValues(first, second, third, fourth);
        final Values other = new ArrayOfValues(second, third);
        MatcherAssert.assertThat(
            "A contained sequence didn't pass.",
            new MatchingSubsequenceTest(2, some, other).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingSubsequenceTest does not pass if the subsequence has a different
     *  label for a value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntPassIfALabelDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 1.0);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A subsequence passed despite having a different label.",
            new MatchingSubsequenceTest(2, some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingSubsequenceTest does not pass if the subsequence has a different
     *  value at some position.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntPassIfAValueDiffers() throws Exception {
        final String label = "3rd";
        final LabelledValue first = new LabelledValue("1st", "1");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue(label, true);
        final LabelledValue alternate = new LabelledValue(label, false);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A subsequence passed despite having a different value.",
            new MatchingSubsequenceTest(2, some, other).passes(),
            CoreMatchers.is(false)
        );
    }
}
