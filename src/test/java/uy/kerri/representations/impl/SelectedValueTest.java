package uy.kerri.representations.impl;

import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.fake.FakeOutput;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public final class SelectedValueTest {
    @Test
    public void selectsAString() throws Exception {
        final String field = "field";
        final String value = "value";
        MatcherAssert.assertThat(
            "A string value wasn't selected.",
            new SelectedStringValue(
                new ArrayOfFields(
                    new LabelledValue(field, value)
                ),
                new SelectedFieldOutput(field)
            ).value(),
            equalTo(value)
        );
    }

    @Test
    public void selectsAnInteger() throws Exception {
        final String field = "Integer";
        final Integer value = 20;
        MatcherAssert.assertThat(
            "An integer value wasn't selected.",
            new SelectedIntegerValue(
                new ArrayOfFields(
                    new LabelledValue(field, value)
                ),
                new SelectedFieldOutput(field)
            ).value(),
            equalTo(value)
        );
    }

    @Test
    public void selectsABoolean() throws Exception {
        final Integer index = 1;
        final Boolean value = true;
        MatcherAssert.assertThat(
            "A boolean value wasn't selected.",
            new SelectedBooleanValue(
                new ArrayOfValues(
                    new LabelledValue("label", value)
                ),
                new SelectedIndexOutput(index)
            ).value(),
            is(value)
        );
    }

    @Test
    public void selectsALong() throws Exception {
        final String field = "Long";
        final Long value = 20L;
        MatcherAssert.assertThat(
            "A long value wasn't selected.",
            new SelectedLongValue(
                new ArrayOfFields(
                    new LabelledValue(field, value)
                ),
                new SelectedFieldOutput(field)
            ).value(),
            equalTo(value)
        );
    }

    @Test
    public void selectsADouble() throws Exception {
        final String field = "Double";
        final Double value = 1.2;
        MatcherAssert.assertThat(
            "A double value wasn't selected.",
            new SelectedDoubleValue(
                new ArrayOfFields(
                    new LabelledValue(field, value)
                ),
                new SelectedFieldOutput(field)
            ).value(),
            equalTo(value)
        );
    }

    @Test
    public void selectsFieldsFromAField() throws Exception {
        final String field = "CompositeF";
        final LabelledValue first = new LabelledValue("1stF", "1stV");
        final LabelledValue second = new LabelledValue("2ndF", "2ndV");
        MatcherAssert.assertThat(
            "A composite value wasn't selected from a field.",
            new MatchingFieldsTest(
                new ArrayOfFields(first, second),
                new SelectedFields(
                    field,
                    new ArrayOfFields(
                        new LabelledValue("fillerF", "fillerV"),
                        new LabelledValue(
                            field, new ArrayOfFields(first, second)
                        ),
                        new LabelledValue("someF", "someV")
                    )
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void selectsFieldsFromASequence() throws Exception {
        final LabelledValue first = new LabelledValue("firstI", "firstV");
        final LabelledValue second = new LabelledValue("secondI", "seconfV");
        MatcherAssert.assertThat(
            "A composite value wasn't selected from a field.",
            new MatchingFieldsTest(
                new ArrayOfFields(first, second),
                new SelectedFields(
                    2,
                    new ArrayOfValues(
                        new LabelledValue("my1stIndex", "my1stValue"),
                        new LabelledValue(
                            "CompositeIndex", new ArrayOfFields(first, second)
                        ),
                        new LabelledValue("my3rdIndex", "my3rdValue")
                    )
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void selectsASequenceFromAField() throws Exception {
        final String field = "SequenceField";
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", "2ndValue");
        MatcherAssert.assertThat(
            "A sequence of values wasn't selected from a field.",
            new MatchingValuesTest(
                new ArrayOfValues(first, second),
                new SelectedValues(
                    field,
                    new ArrayOfFields(
                        new LabelledValue("whatever", "not important"),
                        new LabelledValue(
                            field, new ArrayOfValues(first, second)
                        ),
                        new LabelledValue("filler", "just a filler")
                    )
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void selectsASequenceFromASequence() throws Exception {
        final LabelledValue first = new LabelledValue("1stIndex", "1stVal");
        final LabelledValue second = new LabelledValue("2ndIndex", "2ndVal");
        MatcherAssert.assertThat(
            "A sequence of values wasn't selected from a field.",
            new MatchingValuesTest(
                new ArrayOfValues(first, second),
                new SelectedValues(
                    2,
                    new ArrayOfValues(
                        new LabelledValue("this", "is"),
                        new LabelledValue(
                            "SequenceIndex", new ArrayOfValues(first, second)
                        ),
                        new LabelledValue("just", "a filler")
                    )
                )
            ).passes(),
            is(true)
        );
    }
}
