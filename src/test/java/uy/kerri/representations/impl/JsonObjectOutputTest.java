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
package uy.kerri.representations.test.fake;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import uk.co.datumedge.hamcrest.json.SameJSONAs;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.JsonArrayOutput;
import uy.kerri.representations.impl.JsonObjectOutput;
import uy.kerri.representations.impl.LabelledString;

/**
 * Tests for {@link uy.kerri.representations.impl.JsonObjectOutput}.
 *
 * @since 1.0
 */
public class JsonObjectOutputTest {
    /**
     * JsonObjectOutput shows an empty JSON if it's empty.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsEmptyStringIfEmpty() throws Exception {
        MatcherAssert.assertThat(
            "Empty output isn't empty.",
            new JsonObjectOutput().show(),
            CoreMatchers.equalTo("{}")
        );
    }

    /**
     * JsonObjectOutput shows preformatted string if not printed.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsPreformattedStringIfNotPrinted() throws Exception {
        final String preformat = "{\"name\":\"Rocko\"}";
        MatcherAssert.assertThat(
            "JSON output isn't showing original values when not printed.",
            new JsonObjectOutput(preformat).show(),
            CoreMatchers.equalTo(preformat)
        );
    }

    /**
     * JsonObjectOutput prints a string field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsLabelledString() throws Exception {
        final String preformat =
            "{\"place\":\"Facultad de Ingeniería, UdelaR\"}";
        final String label = "address";
        final String address = "Julio Herrera y Reissig s/n";
        MatcherAssert.assertThat(
            "String field isn't being printed correctly.",
            new JsonObjectOutput(preformat).print(label, address).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(label, address).toString()
            )
        );
    }

    /**
     * JsonObjectOutput prints an integer field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsIntegerField() throws Exception {
        final String preformat = "{\"name\":\"Enrique Martín\"}";
        final String label = "age";
        final Integer value = 46;
        MatcherAssert.assertThat(
            "Integer field isn't being printed correctly.",
            new JsonObjectOutput(preformat).print(label, value).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(label, value).toString()
            )
        );
    }

    /**
     * JsonObjectOutput prints a boolean field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsBooleanField() throws Exception {
        final String preformat = "{\"username\":\"dmr\"}";
        final String label = "active";
        final Boolean value = false;
        MatcherAssert.assertThat(
            "Boolean field isn't being printed correctly.",
            new JsonObjectOutput(preformat).print(label, value).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(label, value).toString()
            )
        );
    }

    /**
     * JsonObjectOutput prints a double field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsDoubleField() throws Exception {
        final String preformat = "{\"humidity\":\"96%\"}";
        final String label = "pressure";
        final Double value = 29.83;
        MatcherAssert.assertThat(
            "Double field isn't being printed correctly.",
            new JsonObjectOutput(preformat).print(label, value).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(label, value).toString()
            )
        );
    }

    /**
     * JsonObjectOutput prints a long field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsLongField() throws Exception {
        final String preformat =
            "{\"file\":\"Alice In Wonderland (1951) BDrip 1080p x264.mkv\"}";
        final String label = "size";
        final Long value = 4702923681L;
        MatcherAssert.assertThat(
            "Long field isn't being printed correctly.",
            new JsonObjectOutput(preformat).print(label, value).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(label, value).toString()
            )
        );
    }

    /**
     * JsonObjectOutput prints a nested field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsNestedField() throws Exception {
        final String preformat = "{\"band\":\"Hole\"}";
        final String label = "lineup";
        final ArrayOfFields value = new ArrayOfFields(
            new LabelledString("vocals", "Courtney Love"),
            new LabelledString("guitar", "Eric Erlandson"),
            new LabelledString("bass", "Melissa Auf der Maur"),
            new LabelledString("drums", "Patty Schemel")
        );
        MatcherAssert.assertThat(
            "Nested fields aren't being printed correctly.",
            new JsonObjectOutput(preformat).print(label, value).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(
                    label,
                    new JSONObject(
                        value.printTo(new JsonObjectOutput()).show()
                    )
                ).toString()
            )
        );
    }

    /**
     * JsonObjectOutput prints a multivalued field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsMultivaluedField() throws Exception {
        final String preformat = "{\"artist\":\"Silver Jews\"}";
        final String key = "albums";
        final String label = "album";
        final ArrayOfValues values = new ArrayOfValues(
            new LabelledString(label, "Starlite Walker"),
            new LabelledString(label, "The Natural Bridge"),
            new LabelledString(label, "American Water"),
            new LabelledString(label, "Bright Flight"),
            new LabelledString(label, "Tanglewood Numbers"),
            new LabelledString(label, "Lookout Mountain, Lookout Sea")
        );
        MatcherAssert.assertThat(
            "Multivalued field isn't being printed correctly.",
            new JsonObjectOutput(preformat).print(key, values).show(),
            SameJSONAs.sameJSONAs(
                new JSONObject(preformat).put(
                    key,
                    new JSONArray(
                        values.printTo(new JsonArrayOutput()).show()
                    )
                ).toString()
            )
        );
    }
}
