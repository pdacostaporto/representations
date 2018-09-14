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
package uy.kerri.representations.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.test.FieldMatchingOutput}.
 *
 * @since 2.0
 */
public class FieldMatchingOutputTest {
    /**
     * Rule for expecting exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * FieldMatchingOutput matches a string field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesAString() throws Exception {
        final String field = "name";
        final String value = "Lionel Messi";
        MatcherAssert.assertThat(
            "Matching string field didn't match.",
            new FieldMatchingOutput(new LabelledValue(field, value))
            .print("field", "value")
            .print(field, value).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }

    /**
     * FieldMatchingOutput matches an integer field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesAnInteger() throws Exception {
        final String field = "credits";
        final Integer value = 425;
        MatcherAssert.assertThat(
            "Matching integer field didn't match.",
            new FieldMatchingOutput(new LabelledValue(field, value))
            .print("some field", " some value").print(field, value).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }

    /**
     * FieldMatchingOutput matches a boolean field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesABoolean() throws Exception {
        final String field = "extinct";
        final Boolean value = false;
        final Boolean irrelevant = true;
        MatcherAssert.assertThat(
            "Matching boolean field didn't match.",
            new FieldMatchingOutput(new LabelledValue(field, value))
            .print("irrelevant field", irrelevant).print(field, value).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }

    /**
     * FieldMatchingOutput matches a double field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesADouble() throws Exception {
        final String field = "densityAtSTP";
        final Double value = 0.08988;
        final Double irrelevant = 0.08989;
        MatcherAssert.assertThat(
            "Matching double field didn't match.",
            new FieldMatchingOutput(new LabelledValue(field, value))
            .print("whatever", irrelevant).print(field, value).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }

    /**
     * FieldMatchingOutput matches a long field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesALong() throws Exception {
        final String field = "maxNumberOfFiles";
        final Long value = 4294967295L;
        final Long irrelevant = 4294967296L;
        MatcherAssert.assertThat(
            "Matching long field didn't match.",
            new FieldMatchingOutput(new LabelledValue(field, value))
            .print("filler", irrelevant).print(field, value).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }

    /**
     * FieldMatchingOutput matches a composite field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesACompositeField() throws Exception {
        final String field = "company";
        final LabelledValue first = new LabelledValue(
            "chairman", "Omid R. Kordestani"
        );
        final LabelledValue second = new LabelledValue(
            "website", "twitter.com"
        );
        MatcherAssert.assertThat(
            "Matching composite field didn't match.",
            new FieldMatchingOutput(
                new LabelledValue(field, new ArrayOfFields(first, second))
            ).print("not important", "really not important")
            .print(field, new ArrayOfFields(second, first)).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }

    /**
     * FieldMatchingOutput matches a multivalued field.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public final void matchesAMultivaluedField() throws Exception {
        final String field = "transactions";
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue(
            "second", "second value"
        );
        final LabelledValue third = new LabelledValue("third", "third value");
        MatcherAssert.assertThat(
            "Matching multivalued field didn't match.",
            new FieldMatchingOutput(
                new LabelledValue(
                    field, new ArrayOfValues(first, second, third)
                )
            ).print("notImportant", "extremely not important")
            .print(field, new ArrayOfValues(first, second, third)).show(),
            CoreMatchers.equalTo(Boolean.TRUE.toString())
        );
    }
}
