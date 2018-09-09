package uy.kerri.representations.test.impl;

import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Values;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.ContainedFieldTest;
import static org.hamcrest.CoreMatchers.is;

public final class ContainedFieldTestTest {
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
            is(true)
        );
    }

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
            is(true)
        );
    }

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
            is(true)
        );
    }

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
            is(true)
        );
    }

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
            is(true)
        );
    }

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
            is(true)
        );
    }

    @Test
    public void matchesAMultivaluedField() throws Exception {
        final String field = "theEighthField";
        final Values value = new ArrayOfValues(
            new LabelledValue("1stL", "1stAV"),
            new LabelledValue("2ndL", "2ndAV"),
            new LabelledValue("3rdL", "3rdAV")
        );
        MatcherAssert.assertThat(
            "A multivalued field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue("theFirstField", new ArrayOfValues()),
                    new LabelledValue("theSecondField", "theSecondValue"),
                    new LabelledValue("theThirdField", 1L),
                    new LabelledValue("theFourthField", false),
                    new LabelledValue("theFifthField", 1.0),
                    new LabelledValue("theSixthField", 1),
                    new LabelledValue("theSeventhField", new ArrayOfFields()),
                    new LabelledValue(field, value)
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void doesntMatchIfTheValueDiffers() throws Exception {
        final String field = "mySecondField";
        final String value = "expected";
        MatcherAssert.assertThat(
            "A field with a different value was matched.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue("myFirstField", 1),
                    new LabelledValue(field, "actual"),
                    new LabelledValue("myThirdField", 1L)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchIfTheFieldIsNotPresent() throws Exception {
        MatcherAssert.assertThat(
            "A missing field was matched.",
            new ContainedFieldTest(
                new LabelledValue("expectedField", "expectedValue"),
                new ArrayOfFields(
                    new LabelledValue("someFirstField", 1),
                    new LabelledValue("someSecondField", "expectedValue"),
                    new LabelledValue("someThirdField", 1L)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void matchesIfFieldIsReassignedToExpected() throws Exception {
        final String field = "name";
        final String value = "Pablo";
        MatcherAssert.assertThat(
            "A reassigned field didn't match.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue(field, "Robert"),
                    new LabelledValue("lastName", "Rodríguez"),
                    new LabelledValue(field, value)
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void doesntMatchIfReassignedToAnotherValue() throws Exception {
        final String field = "id";
        final Integer value = 1;
        MatcherAssert.assertThat(
            "A reassigned field did match when it shouldn't.",
            new ContainedFieldTest(
                new LabelledValue(field, value),
                new ArrayOfFields(
                    new LabelledValue(field, value),
                    new LabelledValue("title", "First post"),
                    new LabelledValue(field, 0)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void doesntPassIfThereAreNoFields() throws Exception {
        MatcherAssert.assertThat(
            "A field was matched in an empty field group.",
            new ContainedFieldTest(
                new LabelledValue("whateverField", "whateverValue"),
                new ArrayOfFields()
            ).passes(),
            is(false)
        );
    }
}
