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

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.test.MatchingFieldsTest;

/**
 * Tests for {@link uy.kerri.representations.select.SelectedFields}.
 *
 * @since 2.0
 */
public final class SelectedFieldsTest {
    /**
     * SelectedFields allows to select a composite value from a field.
     *
     * @throws Exception if something goes wrong.
     */
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
            CoreMatchers.is(true)
        );
    }

    /**
     * SelectedFields allows to select a composite value from a sequence.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void selectsFieldsFromASequence() throws Exception {
        final LabelledValue first = new LabelledValue("firstI", "firstV");
        final LabelledValue second = new LabelledValue("secondI", "seconfV");
        MatcherAssert.assertThat(
            "A composite value wasn't selected from a sequence.",
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
            CoreMatchers.is(true)
        );
    }
}
