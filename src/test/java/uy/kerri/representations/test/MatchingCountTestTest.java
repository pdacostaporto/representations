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
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.test.MatchingCountTest}.
 *
 * @since 2.0
 */
public final class MatchingCountTestTest {
    /**
     * MatchingCountTest passes if the sequences have the same length.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesTwoEqualLengthSequences() throws Exception {
        MatcherAssert.assertThat(
            "Two sequences of the same length didn't match",
            new MatchingCountTest(
                new ArrayOfValues(
                    new LabelledValue("first", "first value"),
                    new LabelledValue("second", "second value"),
                    new LabelledValue("third", "third value")
                ),
                new ArrayOfValues(
                    new LabelledValue("fourth", "fourth value"),
                    new LabelledValue("fifth", "fifth value"),
                    new LabelledValue("sixth", "sixth value")
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingCountTest doesn't pass if the second sequence is longer than the
     *  first.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchWithALongerSequence() throws Exception {
        MatcherAssert.assertThat(
            "A sequence matched with a longer one.",
            new MatchingCountTest(
                new ArrayOfValues(
                    new LabelledValue("1st", "1st value"),
                    new LabelledValue("2nd", "2nd value"),
                    new LabelledValue("3rd", "3rd value")
                ),
                new ArrayOfValues(
                    new LabelledValue("4th", "4th value"),
                    new LabelledValue("5th", "5th value"),
                    new LabelledValue("6th", "6th value"),
                    new LabelledValue("7th", "7th value")
                )
            ).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingCountTest doesn't pass if the second sequence is shorter than the
     *  first.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchWithAShorterSequence() throws Exception {
        MatcherAssert.assertThat(
            "A sequence matched with a shorter one.",
            new MatchingCountTest(
                new ArrayOfValues(
                    new LabelledValue("1st label", "1"),
                    new LabelledValue("2nd label", "2"),
                    new LabelledValue("3rd label", "3")
                ),
                new ArrayOfValues(
                    new LabelledValue("4th label", "4"),
                    new LabelledValue("5th label", "5")
                )
            ).passes(),
            CoreMatchers.is(false)
        );
    }
}
