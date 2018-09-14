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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link uy.kerri.representations.select.SelectedValueOutput}.
 *
 * @since 1.0
 */
public class SelectedValueOutputTest {
    /**
     * Rule for expecting exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * SelectedValueOutput allows to select the first value printed on it if it
     *  is a string.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void allowsToSelectAString() throws Exception {
        final String value = "some value";
        MatcherAssert.assertThat(
            "The correct string wasn't selected.",
            new SelectedValueOutput().print("whatever", value)
            .print("irrelevant", "irrelevant value").show(),
            CoreMatchers.equalTo(value)
        );
    }

    /**
     * SelectedValueOutput allows to select the first value printed on it if it
     *  is an integer.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void allowsToSelectAnInteger() throws Exception {
        final Integer value = 50;
        MatcherAssert.assertThat(
            "The correct integer wasn't selected.",
            new SelectedValueOutput().print("not important", value)
            .print("something", "another value").show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedValueOutput allows to select the first value printed on it if it
     *  is a boolean.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void allowsToSelectABoolean() throws Exception {
        final Boolean value = true;
        MatcherAssert.assertThat(
            "The correct boolean wasn't selected.",
            new SelectedValueOutput().print("field", value)
            .print("whatever field", "whatever value").show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedValueOutput allows to select the first value printed on it if it
     *  is a double.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void allowsToSelectADouble() throws Exception {
        final Double value = 0.08988;
        MatcherAssert.assertThat(
            "The correct double wasn't selected.",
            new SelectedValueOutput().print("label", value)
            .print("trailing", "this is a filler").show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedValueOutput allows to select the first value printed on it if it
     *  is a long.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void allowsToSelectALong() throws Exception {
        final Long value = 4294967295L;
        MatcherAssert.assertThat(
            "The correct long wasn't selected.",
            new SelectedValueOutput().print("selected", value)
            .print("not selected", "this value must not be selected").show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedValueOutput throws ValueNotSelectedException if nothing has been
     *  printed on it.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void throwsIfNotPrinted() throws Exception {
        this.thrown.expect(ValueNotSelectedException.class);
        this.thrown.expectMessage(
            CoreMatchers.equalTo("The value wasn't selected yet.")
        );
        new SelectedValueOutput().show();
    }
}
