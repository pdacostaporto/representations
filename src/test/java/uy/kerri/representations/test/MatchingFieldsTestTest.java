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
 * Tests for {@link uy.kerri.representations.test.MatchingFieldsTest}.
 *
 * @since 2.0
 */
public final class MatchingFieldsTestTest {
    /**
     * MatchingFieldsTest matches two equal sets of fields.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void matchesTwoEqualSets() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", true);
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, third, second);
        MatcherAssert.assertThat(
            "Two equal sets of fields didn't match.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingFieldsTest does not pass if the second set misses some field
     *  from the other.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchMissingFields() throws Exception {
        final LabelledValue first = new LabelledValue("1st field", 1.0);
        final LabelledValue second = new LabelledValue("2nd field", 2);
        final LabelledValue third = new LabelledValue(
            "3rd field", new ArrayOfValues()
        );
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, second);
        MatcherAssert.assertThat(
            "A set of fields matched to a set with missing fields.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingFieldsTest does not pass if the second set has some additional
     *  field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchAdditionalFields() throws Exception {
        final LabelledValue first = new LabelledValue("1st", "1st value");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue("3rd", true);
        final LabelledValue fourth = new LabelledValue(
            "4th", new ArrayOfFields()
        );
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, second, third, fourth);
        MatcherAssert.assertThat(
            "A set of fields matched to a set with additional fields.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingFieldsTest does not pass if the sets have the same amount of
     *  fields but one has a different name.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchIfAFieldDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 1L);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final LabelledValue third = new LabelledValue("3rdField", 2);
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, alternate, third);
        MatcherAssert.assertThat(
            "A set of fields matched to a set with a different field.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingFieldsTest does not pass if the sets have different values for a
     *  field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void doesntMatchIfAValueDiffers() throws Exception {
        final String field = "thirdField";
        final LabelledValue first = new LabelledValue(
            "firstField", "firstValue"
        );
        final LabelledValue second = new LabelledValue("secondField", 2);
        final LabelledValue alternate = new LabelledValue(field, 2);
        final LabelledValue third = new LabelledValue(field, 1);
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, alternate, second);
        MatcherAssert.assertThat(
            "A set matched to a set with a different value for a field.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(false)
        );
    }

    /**
     * MatchingFieldsTest consider only the last assigned value for a field with
     *  a single value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void lastAssignCountsForASingleValue() throws Exception {
        final String field = "3F";
        final LabelledValue first = new LabelledValue("1F", "1V");
        final LabelledValue second = new LabelledValue("2F", 2);
        final LabelledValue third = new LabelledValue(field, 1);
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(
            first,
            new LabelledValue(field, 2),
            new LabelledValue(field, "alternate for single"),
            new LabelledValue(field, 1L),
            new LabelledValue(field, false),
            new LabelledValue(field, new ArrayOfFields()),
            second,
            third
        );
        MatcherAssert.assertThat(
            "Last assign of a single field was ignored.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingFieldsTest consider only the last assigned value for a field with
     *  a composite value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void lastAssignCountsForACompositeValue() throws Exception {
        final String field = "3FC";
        final LabelledValue first = new LabelledValue("1FC", "1VC");
        final LabelledValue second = new LabelledValue("2FC", 2);
        final LabelledValue third = new LabelledValue(
            field, new ArrayOfFields()
        );
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(
            first,
            new LabelledValue(field, 2),
            new LabelledValue(field, "alternate for composite"),
            new LabelledValue(field, 1L),
            new LabelledValue(field, 1.0),
            new LabelledValue(field, false),
            new LabelledValue(field, new ArrayOfValues()),
            second,
            third
        );
        MatcherAssert.assertThat(
            "Last assign of a composite field was ignored.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(true)
        );
    }

    /**
     * MatchingFieldsTest consider only the last assigned value for a field with
     *  a sequence of values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void lastAssignCountsForASequenceValue() throws Exception {
        final String field = "3FS";
        final LabelledValue first = new LabelledValue("1FS", "1VS");
        final LabelledValue second = new LabelledValue("2FS", 2);
        final LabelledValue third = new LabelledValue(
            field, new ArrayOfValues()
        );
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(
            first,
            new LabelledValue(field, 2),
            new LabelledValue(field, "alternate for sequence"),
            new LabelledValue(field, 1L),
            new LabelledValue(field, 1.0),
            new LabelledValue(field, false),
            new LabelledValue(field, new ArrayOfFields()),
            second,
            third
        );
        MatcherAssert.assertThat(
            "Last assign of a multivalued field was ignored.",
            new MatchingFieldsTest(some, other).passes(),
            CoreMatchers.is(true)
        );
    }
}
