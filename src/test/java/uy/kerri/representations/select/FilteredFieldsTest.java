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
package uy.kerri.representations.select;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.Field;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.test.MatchingFieldsTest;

/**
 * Tests for {@link uy.kerri.representations.select.FilteredFieldsTest}.
 *
 * @since 2.0
 */
public final class FilteredFieldsTest {
    /**
     * FilteredFields allows to select some fields from a set of fields.
     *
     * @throws Exception if something goes wrong.
     */
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
            CoreMatchers.is(true)
        );
    }
}
