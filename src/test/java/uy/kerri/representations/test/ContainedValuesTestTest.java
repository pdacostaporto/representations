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
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Values;

/**
 * Tests for {@link uy.kerri.representations.test.ContainedValuesTest}.
 *
 * @since 2.0
 */
public final class ContainedValuesTestTest {
    /**
     * ContainedValuesTest passes if a sequence if contained on another.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void passesIfASequenceIsContainedOnAnother() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue(
            "third", new ArrayOfFields()
        );
        final LabelledValue fourth = new LabelledValue("fourth", false);
        final Values container = new ArrayOfValues(
            first, second, third, fourth
        );
        final Values contained = new ArrayOfValues(second, third);
        MatcherAssert.assertThat(
            "A contained sequence didn't pass.",
            new ContainedValuesTest(container, contained).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedValuesTest doesn't pass if the label of some value is different.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void doesntPassIfALabelDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 2);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final Values container = new ArrayOfValues(first, second, third);
        final Values contained = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A sequence passed despite having a different label.",
            new ContainedValuesTest(container, contained).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * ContainedValuesTest doesn't pass if some value is not contained.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void doesntPassIfAValueDiffers() throws Exception {
        final String label = "3rd";
        final LabelledValue first = new LabelledValue("1st", "1");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue(label, true);
        final LabelledValue alternate = new LabelledValue(label, false);
        final Values container = new ArrayOfValues(first, second, third);
        final Values contained = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A sequence passed despite having a different value.",
            new ContainedValuesTest(container, contained).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * ContainedValuesTest passes if duplicate values are also duplicate in the
     *  container sequence.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void passesIfDuplicatesMatch() throws Exception {
        final LabelledValue first = new LabelledValue("firstL", "firstV");
        final LabelledValue second = new LabelledValue("secondL", 2);
        final LabelledValue third = new LabelledValue("thirdL", 1L);
        final LabelledValue fourth = new LabelledValue("fourthL", false);
        final Values container = new ArrayOfValues(
            first, second, third, third, fourth
        );
        final Values contained = new ArrayOfValues(third, second, third);
        MatcherAssert.assertThat(
            "A sequence with the same duplicates didn't match as contained.",
            new ContainedValuesTest(container, contained).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedValuesTest doest not pass if duplicate values are not also
     *  duplicate in the same or greater amount in the container sequence.
     *
     * @throws Exception if something goes wrong
     */
    @Test
    public void doesntPassIfDuplicatesDontMatch() throws Exception {
        final LabelledValue first = new LabelledValue("firstLabel", "firstVal");
        final LabelledValue second = new LabelledValue(
            "secondLabel", new ArrayOfValues()
        );
        final LabelledValue third = new LabelledValue("thirdLabel", true);
        final LabelledValue fourth = new LabelledValue("fourthLabel", 1.0);
        final Values container = new ArrayOfValues(
            first, second, third, third, fourth
        );
        final Values contained = new ArrayOfValues(fourth, second, fourth);
        MatcherAssert.assertThat(
            "A sequence was contained into another with different duplicates.",
            new ContainedValuesTest(container, contained).passes(),
            CoreMatchers.is(false)
        );
    }
}
