package uy.kerri.representations.test.impl;

import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Field;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.MatchingIndexTest;
import static org.hamcrest.CoreMatchers.is;

public final class MatchingIndexTestTest {
    @Test
    public void matchesAStringValue() throws Exception {
        final Integer index = 2;
        final String label = "second";
        final String value = "value";
        MatcherAssert.assertThat(
            "A string value didn't match.",
            new MatchingIndexTest(
                index,
                label,
                value,
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
            is(true)
        );
    }

    @Test
    public void matchesAnIntegerValue() throws Exception {
        final Integer index = 2;
        final String label = "2nd";
        final Integer value = 100;
        MatcherAssert.assertThat(
            "An integer value didn't match.",
            new MatchingIndexTest(
                index,
                label,
                value,
                new ArrayOfValues(
                    new LabelledValue("1st", 1),
                    new LabelledValue(label, value),
                    new LabelledValue("3rd", 1L)
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void matchesABooleanValue() throws Exception {
        final Integer index = 2;
        final String label = "indexNo2";
        final Boolean value = false;
        MatcherAssert.assertThat(
            "A boolean value didn't match.",
            new MatchingIndexTest(
                index,
                label,
                value,
                new ArrayOfValues(
                    new LabelledValue("indexNo1", 1),
                    new LabelledValue(label, value),
                    new LabelledValue("indexNo3", 1L)
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void matchesADoubleValue() throws Exception {
        final Integer index = 2;
        final String label = "2ndIndex";
        final Double value = 100.1;
        MatcherAssert.assertThat(
            "A double value didn't match.",
            new MatchingIndexTest(
                index,
                label,
                value,
                new ArrayOfValues(
                    new LabelledValue("1stIndex", 1),
                    new LabelledValue(label, value),
                    new LabelledValue("3rdIndex", 1L)
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void matchesALongValue() throws Exception {
        final Integer index = 1;
        final String label = "1stI";
        final Long value = 100L;
        MatcherAssert.assertThat(
            "A long value didn't match.",
            new MatchingIndexTest(
                index,
                label,
                value,
                new ArrayOfValues(
                    new LabelledValue(label, value),
                    new LabelledValue("2ndI", 1),
                    new LabelledValue("3rdI", 1L)
                )
            ).passes(),
            is(true)
        );
    }

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
                label,
                new ArrayOfFields(fields),
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
            is(true)
        );
    }

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
                label,
                new ArrayOfValues(values),
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
            is(true)
        );
    }

    @Test
    public void doesntMatchIfTheSingleValueDiffers() throws Exception {
        final Integer index = 2;
        final String label = "mySecondIndex";
        final String value = "expected";
        MatcherAssert.assertThat(
            "An index with a different single value was matched.",
            new MatchingIndexTest(
                index,
                label,
                value,
                new ArrayOfValues(
                    new LabelledValue("myFirstIndex", 1),
                    new LabelledValue(label, "actual"),
                    new LabelledValue("myThirdIndex", value)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchIfTheCompositeValueDiffers() throws Exception {
        final Integer index = 2;
        final String label = "secondIdx";
        final Fields value = new ArrayOfFields(
            new LabelledValue("myField", "myValue")
        );
        MatcherAssert.assertThat(
            "An index with a different composite value was matched.",
            new MatchingIndexTest(
                index,
                label,
                value,
                new ArrayOfValues(
                    new LabelledValue("firstIdx", 1),
                    new LabelledValue(label, new ArrayOfFields()),
                    new LabelledValue("thirdIdx", value)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchIfTheSequenceDiffers() throws Exception {
        final Integer index = 2;
        final String label = "2ndIdx";
        final Values values = new ArrayOfValues(
            new LabelledValue("theLabel", "theValue")
        );
        MatcherAssert.assertThat(
            "An index with a different sequence of values was matched.",
            new MatchingIndexTest(
                index,
                label,
                values,
                new ArrayOfValues(
                    new LabelledValue("1stIdx", "this is the first value"),
                    new LabelledValue(label, new ArrayOfValues()),
                    new LabelledValue("3rdIdx", values)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void checksTheLabelForASingleValue() throws Exception {
        final String value = "expectedValue";
        MatcherAssert.assertThat(
            "A single value with a different label was matched.",
            new MatchingIndexTest(
                2,
                "expectedLabel",
                value,
                new ArrayOfValues(
                    new LabelledValue("someFirstIndex", 1),
                    new LabelledValue("someSecondIndex", value),
                    new LabelledValue("someThirdIndex", 1L)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void checksTheLabelForACompositeValue() throws Exception {
        final Fields value = new ArrayOfFields();
        MatcherAssert.assertThat(
            "A composite value with a different label was matched.",
            new MatchingIndexTest(
                2,
                "thisExpectedLabel",
                value,
                new ArrayOfValues(
                    new LabelledValue("thisFirstIndex", 1.0),
                    new LabelledValue("thisSecondIndex", value),
                    new LabelledValue("thisThirdIndex", 1L)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void checksTheLabelForASequenceOfValues() throws Exception {
        final Values values = new ArrayOfValues();
        MatcherAssert.assertThat(
            "A sequence of values with a different label was matched.",
            new MatchingIndexTest(
                2,
                "itsTheExpectedLabel",
                values,
                new ArrayOfValues(
                    new LabelledValue("itsTheFirstIndex", true),
                    new LabelledValue("itsTheSecondIndex", values),
                    new LabelledValue("itsTheThirdIndex", 1L)
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void doesntPassIfThereAreNoValues() throws Exception {
        MatcherAssert.assertThat(
            "A value was matched in an empty sequence of values.",
            new MatchingIndexTest(
                1, "whateverLabel", "whateverValue", new ArrayOfValues()
            ).passes(),
            is(false)
        );
    }
}
