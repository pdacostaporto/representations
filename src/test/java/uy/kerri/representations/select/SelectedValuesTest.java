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
import uy.kerri.representations.test.MatchingValuesTest;

/**
 * Tests for {@link uy.kerri.representations.select.SelectedValues}.
 *
 * @since 2.0
 */
public final class SelectedValuesTest {
    /**
     * SelectedValues allows to a sequence of values from a field.
     *
     * @throws Exception if something goes wrong.
     */
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
            CoreMatchers.is(true)
        );
    }

    /**
     * SelectedValues allows to select a sequence of values from a sequence of
     *  values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void selectsASequenceFromASequence() throws Exception {
        final LabelledValue first = new LabelledValue("1stIndex", "1stVal");
        final LabelledValue second = new LabelledValue("2ndIndex", "2ndVal");
        MatcherAssert.assertThat(
            "A sequence of values wasn't selected from a sequence.",
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
            CoreMatchers.is(true)
        );
    }
}
