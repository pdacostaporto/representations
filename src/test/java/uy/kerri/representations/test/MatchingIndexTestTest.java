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
import uy.kerri.representations.Field;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * Tests for {@link uy.kerri.representations.test.MatchingIndexTest}.
 *
 * @since 2.0
 */
public final class MatchingIndexTestTest {
    /**
     * MatchingIndexTest matches an index with a string value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesAStringValue() throws Exception {
        final Integer index = 2;
        final String label = "second";
        final String value = "value";
        MatcherAssert.assertThat(
            "A string value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, value),
                new ArrayOfValues(
                    new LabelledValue(
                        "first",
                        new ArrayOfFields(
                            new LabelledValue("nestedField1", "nestedValue1"),
                            new LabelledValue("nestedField2", "nestedValue2"),
                            new LabelledValue("nestedField3", "nestedValue3")
                        )
                    ),
                    new LabelledValue(label, value),
                    new LabelledValue("third", 1L),
                    new LabelledValue("fourth", 1),
                    new LabelledValue(
                        "fifth",
                        new ArrayOfValues(
                            new LabelledValue("label1", "index1"),
                            new LabelledValue("label2", "index2"),
                            new LabelledValue("label3", "index3")
                        )
                    )
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest matches an index with an integer value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesAnIntegerValue() throws Exception {
        final Integer index = 2;
        final String label = "2nd";
        final Integer value = 100;
        MatcherAssert.assertThat(
            "An integer value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, value),
                new ArrayOfValues(
                    new LabelledValue("1st", 1),
                    new LabelledValue(label, value),
                    new LabelledValue("3rd", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest matches an index with a boolean value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesABooleanValue() throws Exception {
        final Integer index = 2;
        final String label = "indexNo2";
        final Boolean value = false;
        MatcherAssert.assertThat(
            "A boolean value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, value),
                new ArrayOfValues(
                    new LabelledValue("indexNo1", 1),
                    new LabelledValue(label, value),
                    new LabelledValue("indexNo3", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest matches an index with a double value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesADoubleValue() throws Exception {
        final Integer index = 2;
        final String label = "2ndIndex";
        final Double value = 100.1;
        MatcherAssert.assertThat(
            "A double value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, value),
                new ArrayOfValues(
                    new LabelledValue("1stIndex", 1),
                    new LabelledValue(label, value),
                    new LabelledValue("3rdIndex", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest matches an index with a long value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesALongValue() throws Exception {
        final Integer index = 1;
        final String label = "1stI";
        final Long value = 100L;
        MatcherAssert.assertThat(
            "A long value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, value),
                new ArrayOfValues(
                    new LabelledValue(label, value),
                    new LabelledValue("2ndI", 1),
                    new LabelledValue("3rdI", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest matches an index with a composite value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesACompositeValue() throws Exception {
        final Integer index = 2;
        final String label = "secondIndex";
        final Field[] fields = new Field[] {
            new LabelledValue("1stN", "1stNV"),
            new LabelledValue("2ndN", "2ndNV"),
            new LabelledValue("3rdN", "3rdNV"),
        };
        MatcherAssert.assertThat(
            "A composite value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, new ArrayOfFields(fields)),
                new ArrayOfValues(
                    new LabelledValue("firstField", 1),
                    new LabelledValue(label, new ArrayOfFields(fields)),
                    new LabelledValue("thirdField", 1L),
                    new LabelledValue("fourthField", new ArrayOfFields()),
                    new LabelledValue("sixthField", new ArrayOfValues()),
                    new LabelledValue("seventhField", false),
                    new LabelledValue("eighthField", 1.0),
                    new LabelledValue("ninthField", "ninthValue")
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest matches an index with a sequence of values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesAMultivaluedIndex() throws Exception {
        final Integer index = 2;
        final String label = "theSecondIndex";
        final Value[] values = new Value[] {
            new LabelledValue("1stL", "1stAV"),
            new LabelledValue("2ndL", "2ndAV"),
            new LabelledValue("3rdL", "3rdAV"),
        };
        MatcherAssert.assertThat(
            "A multivalued value didn't match.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, new ArrayOfValues(values)),
                new ArrayOfValues(
                    new LabelledValue("theFirstIndex", new ArrayOfValues()),
                    new LabelledValue(label, new ArrayOfValues(values)),
                    new LabelledValue("theThirdIndex", 1L),
                    new LabelledValue("theFourthIndex", false),
                    new LabelledValue("theFifthIndex", 1.0),
                    new LabelledValue("theSixthIndex", 1),
                    new LabelledValue("theSeventhIndex", new ArrayOfFields())
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingIndexTest does not pass if the index has a different value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchIfTheValueDiffers() throws Exception {
        final Integer index = 2;
        final String label = "2ndIdx";
        final Values values = new ArrayOfValues(
            new LabelledValue("theLabel", "theValue")
        );
        MatcherAssert.assertThat(
            "An index with a different value was matched.",
            new MatchingIndexTest(
                index,
                new LabelledValue(label, values),
                new ArrayOfValues(
                    new LabelledValue("1stIdx", "this is the first value"),
                    new LabelledValue(label, new ArrayOfValues()),
                    new LabelledValue("3rdIdx", values)
                )
            ).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingIndexTest does not pass if the index has a the correct value with
     *  a different label.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchIfTheLabelDiffers() throws Exception {
        final Values values = new ArrayOfValues();
        MatcherAssert.assertThat(
            "A value with a different label was matched.",
            new MatchingIndexTest(
                2,
                new LabelledValue("itsTheExpectedLabel", values),
                new ArrayOfValues(
                    new LabelledValue("itsTheFirstIndex", true),
                    new LabelledValue("itsTheSecondIndex", values),
                    new LabelledValue("itsTheThirdIndex", 1L)
                )
            ).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingIndexTest does not pass if there are no values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntPassIfThereAreNoValues() throws Exception {
        MatcherAssert.assertThat(
            "A value was matched in an empty sequence of values.",
            new MatchingIndexTest(
                1,
                new LabelledValue("whateverLabel", "whateverValue"),
                new ArrayOfValues()
            ).passes(),
            CoreMatchers.is(false)
        );
    }
}
