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
import uy.kerri.representations.Fields;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.test.ContainedFieldTest}.
 *
 * @since 2.0
 */
public final class ContainedFieldTestTest {
    /**
     * ContainedFieldTest allows to match a string field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesAStringField() throws Exception {
        final String field = "second";
        final String value = "value";
        MatcherAssert.assertThat(
            "A string field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue(
                        "first",
                        new ArrayOfFields(
                            new LabelledValue("nestedField1", "nestedValue1"),
                            new LabelledValue("nestedField2", "nestedValue2"),
                            new LabelledValue("nestedField3", "nestedValue3")
                        )
                    ),
                    new LabelledValue(field, value),
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
     * ContainedFieldTest allows to match an integer field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesAnIntegerField() throws Exception {
        final String field = "2nd";
        final Integer value = 100;
        MatcherAssert.assertThat(
            "An integer field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue("1st", 1),
                    new LabelledValue(field, value),
                    new LabelledValue("3rd", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedFieldTest allows to match a boolean field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesABooleanField() throws Exception {
        final String field = "fieldNo2";
        final Boolean value = false;
        MatcherAssert.assertThat(
            "A boolean field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue("fieldNo1", 1),
                    new LabelledValue(field, value),
                    new LabelledValue("fieldNo3", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedFieldTest allows to match a double field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesADoubleField() throws Exception {
        final String field = "2ndField";
        final Double value = 100.1;
        MatcherAssert.assertThat(
            "A double field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue("1stField", 1),
                    new LabelledValue(field, value),
                    new LabelledValue("3rdField", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedFieldTest allows to match a long field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesALongField() throws Exception {
        final String field = "1stF";
        final Long value = 100L;
        MatcherAssert.assertThat(
            "A long field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue(field, value),
                    new LabelledValue("2ndF", 1),
                    new LabelledValue("3rdF", 1L)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedFieldTest allows to match a composite field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesACompositeField() throws Exception {
        final String field = "secondField";
        final Fields value = new ArrayOfFields(
            new LabelledValue("1stN", "1stNV"),
            new LabelledValue("2ndN", "2ndNV"),
            new LabelledValue("3rdN", "3rdNV")
        );
        MatcherAssert.assertThat(
            "A composite field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue("firstField", 1),
                    new LabelledValue(field, value),
                    new LabelledValue("thirdField", 1L),
                    new LabelledValue("fourthField", new ArrayOfFields()),
                    new LabelledValue("sixthField", new ArrayOfValues()),
                    new LabelledValue("seventhField", false),
                    new LabelledValue("eighthField", 1.0)
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedFieldTest allows to match a multivalued field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesAMultivaluedField() throws Exception {
        final String field = "theEighthField";
        final LabelledValue expected = new LabelledValue(
            field,
            new ArrayOfValues(
                new LabelledValue("1stL", "1stAV"),
                new LabelledValue("2ndL", "2ndAV"),
                new LabelledValue("3rdL", "3rdAV")
            )
        );
        MatcherAssert.assertThat(
            "A multivalued field didn't match.",
            new ContainedFieldTest(
                expected,
                new ArrayOfFields(
                    new LabelledValue(field, "a value to be reassigned"),
                    new LabelledValue("theFirstField", new ArrayOfValues()),
                    new LabelledValue("theSecondField", "theSecondValue"),
                    new LabelledValue("theThirdField", 1L),
                    new LabelledValue("theFourthField", false),
                    new LabelledValue("theFifthField", 1.0),
                    new LabelledValue("theSixthField", 1),
                    new LabelledValue("theSeventhField", new ArrayOfFields()),
                    expected
                )
            ).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * ContainedFieldTest doesn't pass if the field is present but has a
     *  different value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntPassIfTheValueDiffers() throws Exception {
        final String field = "mySecondField";
        final LabelledValue expected = new LabelledValue(
            field, "expected"
        );
        MatcherAssert.assertThat(
            "A field with a different value was matched.",
            new ContainedFieldTest(
                expected,
                new ArrayOfFields(
                    new LabelledValue("myFirstField", 1),
                    expected,
                    new LabelledValue(field, "actual"),
                    new LabelledValue("myThirdField", 1L)
                )
            ).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * ContainedFieldTest doesn't pass if the field is not present.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntPassIfTheFieldIsNotPresent() throws Exception {
        final String value = "expectedValue";
        MatcherAssert.assertThat(
            "A missing field was matched.",
            new ContainedFieldTest(
                new LabelledValue("expectedField", value),
                new ArrayOfFields(
                    new LabelledValue("someFirstField", 1),
                    new LabelledValue("someSecondField", value),
                    new LabelledValue("someThirdField", 1L)
                )
            ).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * ContainedFieldTest doesn't pass if there are no fields.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntPassIfThereAreNoFields() throws Exception {
        MatcherAssert.assertThat(
            "A field was matched in an empty field group.",
            new ContainedFieldTest(
                new LabelledValue("whateverField", "whateverValue"),
                new ArrayOfFields()
            ).passes(),
            CoreMatchers.is(false)
        );
    }
}
