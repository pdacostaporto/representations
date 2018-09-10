package uy.kerri.representations.impl;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.fake.FakeOutput;
import uy.kerri.representations.Field;
import static org.hamcrest.CoreMatchers.is;

public final class FilteredFieldsTest {
    @Test
    public void selectsFields() throws Exception {
        final List<String> fields = Arrays.asList(
            "Boolean", "Composite", "Multivalued"
        );
        final Field third = new LabelledValue(fields.get(0), true);
        final Field sixth = new LabelledValue(
            fields.get(1),
            new ArrayOfFields(
                new LabelledValue("1F", "1CV"),
                new LabelledValue("2F", "2CV")
            )
        );
        final Field seventh = new LabelledValue(
            fields.get(2),
            new ArrayOfValues(
                new LabelledValue("1L", "1MV"),
                new LabelledValue("2L", "2MV")
            )
        );
        MatcherAssert.assertThat(
            new MatchingFieldsTest(
                new ArrayOfFields(third, sixth, seventh),
                new FilteredFields(
                    fields,
                    new ArrayOfFields(
                        new LabelledValue("Integer", 1),
                        new LabelledValue("String", "string value"),
                        third,
                        new LabelledValue("Double", 1.0),
                        new LabelledValue("Long", 1L),
                        sixth,
                        seventh
                    )
                )
            ).passes(),
            is(true)
        );
    }
}
