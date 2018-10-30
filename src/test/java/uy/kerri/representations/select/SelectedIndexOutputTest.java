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

import org.hamcrest.Matchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for index selection with
 *  {@link uy.kerri.representations.select.SelectedOutput}.
 *
 * @since 2.0
 */
public class SelectedIndexOutputTest {
    /**
     * Rule for expecting exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * SelectedOutput allows to select a string between a sequence
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
            ).printTo(new SelectedOutput(index)).show(),
            Matchers.equalTo(value)
        );
    }

    /**
     * SelectedOutput allows to select an integer between a sequence
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
            ).printTo(new SelectedOutput(index)).show(),
            Matchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedOutput allows to select a boolean between a sequence of
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
            ).printTo(new SelectedOutput(index)).show(),
            Matchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedOutput allows to select a boolean between a sequence of
     *  values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsADouble() throws Exception {
        final Double value = 0.08988;
        final Double fusion = 0.117;
        final Integer index = 4;
        MatcherAssert.assertThat(
            "The correct double value wasn't selected.",
            Double.valueOf(
                new ArrayOfValues(
                    new LabelledValue("heatOfFusion", fusion),
                    new LabelledValue("symbol", "H"),
                    new LabelledValue("atomicNumber", 1),
                    new LabelledValue("densityAtSTP", value)
                ).printTo(new SelectedOutput(index)).show()
            ),
            Matchers.equalTo(value)
        );
    }

    /**
     * SelectedOutput allows to select a long between a sequence of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsALong() throws Exception {
        final String field = "maxNumberOfFiles";
        final Long value = 4294967295L;
        final Integer introduced = 1997;
        final Integer index = 1;
        MatcherAssert.assertThat(
            "The correct value wasn't selected.",
            new ArrayOfValues(
                new LabelledValue(field, value),
                new LabelledValue("introduced", introduced),
                new LabelledValue("developer", "Microsoft"),
                new LabelledValue("acronym", "NTFS")
            ).printTo(new SelectedOutput(index)).show(),
            Matchers.equalTo(value.toString())
        );
    }

    /**
     * SelectedOutput allows to select a composite value between a
     *  sequence of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsACompositeValue() throws Exception {
        final Integer index = 2;
        final String field = "second";
        final Double value = 2.2;
        MatcherAssert.assertThat(
            "The nested value wasn't selected.",
            Double.valueOf(
                new ArrayOfValues(
                    new LabelledValue("first", new ArrayOfFields()),
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
                    new SelectedOutput(
                        index,
                        new SelectedOutput(field)
                    )
                ).show()
            ),
            Matchers.equalTo(value)
        );
    }

    /**
     * SelectedOutput allows to select a sequence of values between a
     *  sequence of values.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void selectsASequenceOfValues() throws Exception {
        final String value = "2nd value";
        final Boolean first = true;
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
                new SelectedOutput(outer, new SelectedOutput(inner))
            ).show(),
            Matchers.equalTo(value)
        );
    }

    /**
     * SelectedOutput throws ValueNotSelectedException if the index isn't
     *  present.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void throwsIfNotPresent() throws Exception {
        final Integer index = 3;
        this.thrown.expect(ValueNotSelectedException.class);
        this.thrown.expectMessage(
            Matchers.equalTo(
                String.format("The index %d wasn't selected yet.", index)
            )
        );
        new ArrayOfValues(
            new LabelledValue("values", new ArrayOfValues()),
            new LabelledValue(
                "my",
                new ArrayOfFields(
                    new LabelledValue("try", 2),
                    new LabelledValue("go", 2.0),
                    new LabelledValue("bed", "now")
                )
            )
        ).printTo(new SelectedOutput(index)).show();
    }
}
