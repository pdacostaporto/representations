package uy.kerri.representations.test.impl;

import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.SelectedBooleanValue;
import uy.kerri.representations.impl.SelectedDoubleValue;
import uy.kerri.representations.impl.SelectedIntegerValue;
import uy.kerri.representations.impl.SelectedLongValue;
import uy.kerri.representations.impl.SelectedStringValue;
import uy.kerri.representations.impl.SelectedFieldOutput;
import uy.kerri.representations.impl.SelectedIndexOutput;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public final class SelectedSingleValueTest {
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
}
