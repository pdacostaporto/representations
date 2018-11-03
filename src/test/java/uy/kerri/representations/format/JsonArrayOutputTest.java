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
package uy.kerri.representations.format;

import org.hamcrest.Matchers;
import org.hamcrest.junit.MatcherAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import uk.co.datumedge.hamcrest.json.SameJSONAs;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.format.JsonArrayOutput}.
 *
 * @since 2.0
 */
public class JsonArrayOutputTest {
    /**
     * JsonArrayOutput shows an empty JSON if it's empty.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsEmptyStringIfEmpty() throws Exception {
        MatcherAssert.assertThat(
            "Empty output isn't empty.",
            new JsonArrayOutput().show(),
            Matchers.equalTo("[]")
        );
    }

    /**
     * JsonArrayOutput shows preformatted string if not printed.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsPreformattedStringIfNotPrinted() throws Exception {
        final String preformat = "[\"Rocko\"]";
        MatcherAssert.assertThat(
            "JSON array output isn't showing original values when not printed.",
            new JsonArrayOutput(preformat).show(),
            Matchers.equalTo(preformat)
        );
    }

    /**
     * JsonArrayOutput prints a string value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsStringValue() throws Exception {
        final String preformat = "[\"Facultad de Ingeniería, UdelaR\",27]";
        final String value = "Massachusetts Institute of Technology";
        MatcherAssert.assertThat(
            "String value isn't being printed correctly.",
            new JsonArrayOutput(preformat).print("whatever", value).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(value).toString()
            )
        );
    }

    /**
     * JsonArrayOutput prints an integer value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsIntegerValue() throws Exception {
        final String preformat = "[18,14,\"random\"]";
        final Integer value = 46;
        MatcherAssert.assertThat(
            "Integer value isn't being printed correctly.",
            new JsonArrayOutput(preformat).print("ignored", value).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(value).toString()
            )
        );
    }

    /**
     * JsonArrayOutput prints a boolean value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsBooleanValue() throws Exception {
        final String preformat = "[true,\"false\",false]";
        final Boolean value = false;
        MatcherAssert.assertThat(
            "Boolean value isn't being printed correctly.",
            new JsonArrayOutput(preformat).print("dummy", value).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(value).toString()
            )
        );
    }

    /**
     * JsonArrayOutput prints a double value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsDoubleValue() throws Exception {
        final String preformat = "[\"humidity\", [1,0.4,\"96%\"]]";
        final Double value = 29.83;
        MatcherAssert.assertThat(
            "Double value isn't being printed correctly.",
            new JsonArrayOutput(preformat).print("filler", value).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(value).toString()
            )
        );
    }

    /**
     * JsonArrayOutput prints a long value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsLongValue() throws Exception {
        final String preformat = "[67, true, \"something\"]";
        final Long value = 4702923681L;
        MatcherAssert.assertThat(
            "Long value isn't being printed correctly.",
            new JsonArrayOutput(preformat).print("irrelevant", value).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(value).toString()
            )
        );
    }

    /**
     * JsonArrayOutput prints a nested value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsNestedField() throws Exception {
        final String preformat = "[\"garbage\",[1,2,3],277]";
        final ArrayOfFields value = new ArrayOfFields(
            new LabelledValue("vocals", "Courtney Love"),
            new LabelledValue("guitar", "Eric Erlandson"),
            new LabelledValue("bass", "Melissa Auf der Maur"),
            new LabelledValue("drums", "Patty Schemel")
        );
        MatcherAssert.assertThat(
            "Nested values aren't being printed correctly.",
            new JsonArrayOutput(preformat).print("nothing", value).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(
                    new JSONObject(
                        value.printTo(new JsonObjectOutput()).show()
                    )
                ).toString()
            )
        );
    }

    /**
     * JsonArrayOutput prints a multivalued value.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsMultivaluedField() throws Exception {
        final String preformat = "[100]";
        final String label = "album";
        final ArrayOfValues values = new ArrayOfValues(
            new LabelledValue(label, "Starlite Walker"),
            new LabelledValue(label, "The Natural Bridge"),
            new LabelledValue(label, "American Water"),
            new LabelledValue(label, "Bright Flight"),
            new LabelledValue(label, "Tanglewood Numbers"),
            new LabelledValue(label, "Lookout Mountain, Lookout Sea")
        );
        MatcherAssert.assertThat(
            "Nested array isn't being printed correctly.",
            new JsonArrayOutput(preformat).print("placeholder", values).show(),
            SameJSONAs.sameJSONAs(
                new JSONArray(preformat).put(
                    new JSONArray(
                        values.printTo(new JsonArrayOutput()).show()
                    )
                ).toString()
            )
        );
    }

    /**
     * JsonArrayOutput can be reused.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void canBeReused() throws Exception {
        final JsonArrayOutput output = new JsonArrayOutput();
        output.print("lostLabel", "lostValue");
        MatcherAssert.assertThat(
            "JSON array output wasn't reused in an immutable manner.",
            output.print("persistentLabel", "persistentValue").show(),
            Matchers.equalTo("[\"persistentValue\"]")
        );
    }
}
