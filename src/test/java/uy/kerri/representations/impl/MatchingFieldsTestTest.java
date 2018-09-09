package uy.kerri.representations.test.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Fields;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.MatchingFieldsTest;
import static org.hamcrest.CoreMatchers.is;

public final class MatchingFieldsTestTest {
    @Test
    public void matchesTwoEqualGroups() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", true);
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, third, second);
        MatcherAssert.assertThat(
            "Two equal groups of fields didn't match.",
            new MatchingFieldsTest(some, other).passes(),
            is(true)
        );
    }

    @Test
    public void doesntMatchLessFields() throws Exception {
        final LabelledValue first = new LabelledValue("1st field", 1.0);
        final LabelledValue second = new LabelledValue("2nd field", 2);
        final LabelledValue third = new LabelledValue(
            "3rd field", new ArrayOfValues()
        );
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, second);
        MatcherAssert.assertThat(
            "A group of fields matched to a group with less fields.",
            new MatchingFieldsTest(some, other).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchMoreFields() throws Exception {
        final LabelledValue first = new LabelledValue("1st", "1st value");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue("3rd", true);
        final LabelledValue fourth = new LabelledValue(
                "4th", new ArrayOfFields()
        );
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, second, third, fourth);
        MatcherAssert.assertThat(
            "A group of fields matched to a group with more fields.",
            new MatchingFieldsTest(some, other).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchIfAFieldDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 1L);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final Fields some = new ArrayOfFields(first, second, third);
        final Fields other = new ArrayOfFields(first, alternate, third);
        MatcherAssert.assertThat(
            "A group of fields matched to a group with a different field.",
            new MatchingFieldsTest(some, other).passes(),
            is(false)
        );
    }

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
            "A group matched to a group with a different value for a field.",
            new MatchingFieldsTest(some, other).passes(),
            is(false)
        );
    }

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
            is(true)
        );
    }

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
            is(true)
        );
    }

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
            is(true)
        );
    }
}
