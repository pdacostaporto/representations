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
package uy.kerri.representations.test.impl;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uy.kerri.representations.exception.IndexNotFoundException;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.SelectedFieldOutput;
import uy.kerri.representations.impl.SelectedIndexOutput;

/**
 * Tests for {@link uy.kerri.representations.impl.SelectedIndexOutput}.
 *
 * @since 1.0
 */
public class SelectedIndexOutputTest {
    /**
     * Rule for expecting exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * SelectedIndexOutput allows to select a string between a sequence
     *  of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsAString() throws Exception {
        final String label = "country";
        final String value = "Uruguay";
        final Integer index = 4;
        MatcherAssert.assertThat(
            "The correct string value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue(label, "Argentina"),
                new LabelledValue(label, "Brazil"),
                new LabelledValue(label, "Paraguay"),
                new LabelledValue(label, value),
                new LabelledValue(label, "Venezuela")
            ).printTo(new SelectedIndexOutput(index)).show(),
            CoreMatchers.equalTo(value)
        );
    }

    /**
     * SelectedIndexOutput allows to select an integer between a sequence
     *  of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsAnInteger() throws Exception {
        final Integer value = 443;
        final Integer index = 3;
        final Integer length = 4872;
        final Integer status = 200;
        MatcherAssert.assertThat(
            "The correct integer value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue("address", "api.blizz.io"),
                new LabelledValue(
                    "headers",
                    new ArrayOfValues(
                        new LabelledValue("Connection", "keep-alive"),
                        new LabelledValue("Content-Length", length)
                    )
                ),
                new LabelledValue("port", value),
                new LabelledValue("status", status)
            ).printTo(new SelectedIndexOutput(index)).show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedIndexOutput allows to select a boolean between a sequence of
     *  values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsABoolean() throws Exception {
        final Boolean value = true;
        final Integer posts = 215;
        final Integer index = 5;
        MatcherAssert.assertThat(
            "The correct boolean value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue("username", "zidadin"),
                new LabelledValue("email", "zidadin@kerri.uy"),
                new LabelledValue(
                    "lastPost",
                    new ArrayOfFields(
                        new LabelledValue("subject", "Values"),
                        new LabelledValue("date", "2017-01-01")
                    )
                ),
                new LabelledValue("posts", posts),
                new LabelledValue("verified", value)
            ).printTo(new SelectedIndexOutput(index)).show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedIndexOutput allows to select a boolean between a sequence of
     *  values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsADouble() throws Exception {
        final Double value = 0.08988;
        final Double fusion = 0.117;
        final Integer index = 3;
        MatcherAssert.assertThat(
            "The correct double value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue("symbol", "H"),
                new LabelledValue("atomicNumber", 1),
                new LabelledValue("densityAtSTP", value),
                new LabelledValue("heatOfFusion", fusion)
            ).printTo(new SelectedIndexOutput(index)).show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedIndexOutput allows to select a long between a sequence of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsALong() throws Exception {
        final String field = "maxNumberOfFiles";
        final Long value = 4294967295L;
        final Integer introduced = 1997;
        final Integer index = 4;
        MatcherAssert.assertThat(
            "The correct value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue("introduced", introduced),
                new LabelledValue("developer", "Microsoft"),
                new LabelledValue("acronym", "NTFS"),
                new LabelledValue(field, value)
            ).printTo(new SelectedIndexOutput(index)).show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedIndexOutput allows to select a composite value between a
     *  sequence of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsACompositeValue() throws Exception {
        final Integer index = 2;
        final String field = "second";
        final String value = "expected";
        final Integer first = 200;
        MatcherAssert.assertThat(
            "The nested value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue("first", first),
                new LabelledValue(
                    field,
                    new ArrayOfFields(
                        new LabelledValue("not", "this value"),
                        new LabelledValue(field, value),
                        new LabelledValue("this one", "neither")
                    )
                ),
                new LabelledValue("third", "the third value")
            ).printTo(
                new SelectedIndexOutput(
                    index,
                    new SelectedFieldOutput(field)
                )
            ).show(),
            CoreMatchers.equalTo(value)
        );
    }

    /**
     * SelectedIndexOutput allows to select a sequence of values between a
     *  sequence of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsASequenceOfValues() throws Exception {
        final String value = "2nd value";
        final Integer first = 201;
        final Integer outer = 3;
        final Integer inner = 2;
        MatcherAssert.assertThat(
            "The value in the sequence of values wasn't selected.",
            new ArrayOfValues(
                new LabelledValue("1st", first),
                new LabelledValue("2nd", "the 2nd outer value"),
                new LabelledValue(
                    "3rd",
                    new ArrayOfValues(
                        new LabelledValue("1st index", "1st value"),
                        new LabelledValue("2nd index", value),
                        new LabelledValue("3rd index", "3rd value")
                    )
                )
            ).printTo(
                new SelectedIndexOutput(outer, new SelectedIndexOutput(inner))
            ).show(),
            CoreMatchers.equalTo(value)
        );
    }

    /**
     * SelectedIndexOutput throws IndexNotFoundException if the index isn't
     *  present.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void throwsIfNotPresent() throws Exception {
        final Integer index = 3;
        this.thrown.expect(IndexNotFoundException.class);
        this.thrown.expectMessage(
            CoreMatchers.equalTo("The index is not present.")
        );
        new ArrayOfValues(
            new LabelledValue("this", "is"),
            new LabelledValue(
                "my",
                new ArrayOfFields(
                    new LabelledValue("try", 2),
                    new LabelledValue("go", 2.0),
                    new LabelledValue("bed", "now")
                )
            )
        ).printTo(new SelectedIndexOutput(index)).show();
    }
}
