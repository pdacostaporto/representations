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
package uy.kerri.representations.fake;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.fake.FakeOutput}.
 *
 * @since 1.0
 */
public class FakeOutputTest {
    /**
     * FakeOutput shows an empty string if it's empty.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsEmptyStringIfEmpty() throws Exception {
        MatcherAssert.assertThat(
            "Empty output isn't empty.",
            new FakeOutput().show(),
            CoreMatchers.equalTo("")
        );
    }

    /**
     * FakeOutput shows preformatted string if not printed.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsPreformattedStringIfNotPrinted() throws Exception {
        final String preformat = "name:String:Rocko";
        MatcherAssert.assertThat(
            "Preformatted output isn't printing preformatted string.",
            new FakeOutput(preformat).show(),
            CoreMatchers.equalTo(preformat)
        );
    }

    /**
     * FakeOutput prints a string field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsLabelledValue() throws Exception {
        final String preformat = "place:String:Facultad de Ingeniería, UdelaR";
        final String address = "Julio Herrera y Reissig s/n";
        MatcherAssert.assertThat(
            "String field isn't being printed correctly.",
            new FakeOutput(preformat).print("address", address).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        String.format("address:String:%s", address),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints an integer field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsIntegerField() throws Exception {
        final String preformat = "name:String:Enrique Martín";
        final Integer age = 46;
        MatcherAssert.assertThat(
            "Integer field isn't being printed correctly.",
            new FakeOutput(preformat).print("age", age).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        String.format("age:Integer:%d", age),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints a boolean field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsBooleanField() throws Exception {
        final String preformat = "username:String:dmr";
        final Boolean active = false;
        MatcherAssert.assertThat(
            "Boolean field isn't being printed correctly.",
            new FakeOutput(preformat).print("active", active).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        String.format("active:Boolean:%b", active),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints a double field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsDoubleField() throws Exception {
        final String preformat = "humidity:String:96%";
        final Double pressure = 29.83;
        MatcherAssert.assertThat(
            "Double field isn't being printed correctly.",
            new FakeOutput(preformat).print("pressure", pressure).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        String.format("pressure:Double:%f", pressure),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints a long field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsLongField() throws Exception {
        final String preformat =
            "file:String:Alice In Wonderland (1951) BDrip 1080p x264.mkv";
        final Long size = 4702923681L;
        MatcherAssert.assertThat(
            "Long field isn't being printed correctly.",
            new FakeOutput(preformat).print("size", size).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        String.format("size:Long:%d", size),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints a nested field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsNestedField() throws Exception {
        final String preformat = "band:String:Hole";
        final ArrayOfFields lineup = new ArrayOfFields(
            new LabelledValue("vocals", "Courtney Love"),
            new LabelledValue("guitar", "Eric Erlandson"),
            new LabelledValue("bass", "Melissa Auf der Maur"),
            new LabelledValue("drums", "Patty Schemel")
        );
        MatcherAssert.assertThat(
            "Nested fields aren't being printed correctly.",
            new FakeOutput(preformat).print("lineup", lineup).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        StringUtils.join(
                            ArrayUtils.addAll(
                                new String[] {
                                    String.format("lineup:Nested:"),
                                },
                                lineup.printTo(new FakeOutput())
                                .show()
                                .split(String.format("%n"))
                            ),
                            String.format("%n\t")
                        ),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints a multivalued field.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsMultivaluedField() throws Exception {
        final String preformat = "artist:String:Silver Jews";
        final String label = "album";
        final ArrayOfValues albums = new ArrayOfValues(
            new LabelledValue(label, "Starlite Walker"),
            new LabelledValue(label, "The Natural Bridge"),
            new LabelledValue(label, "American Water"),
            new LabelledValue(label, "Bright Flight"),
            new LabelledValue(label, "Tanglewood Numbers"),
            new LabelledValue(label, "Lookout Mountain, Lookout Sea")
        );
        MatcherAssert.assertThat(
            "Multivalued field isn't being printed correctly.",
            new FakeOutput(preformat).print("albums", albums).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        preformat,
                        StringUtils.join(
                            ArrayUtils.addAll(
                                new String[] {
                                    String.format("albums:Multivalued:"),
                                },
                                albums.printTo(new FakeOutput())
                                .show()
                                .split(String.format("%n"))
                            ),
                            String.format("%n*")
                        ),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * FakeOutput prints a field correctly if originally empty.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void printsCorrectlyIfEmpty() throws Exception {
        final String sentence = "No sea nabo, Neber.";
        MatcherAssert.assertThat(
            "Field not printing correctly if output is empty.",
            new FakeOutput().print("sentence", sentence).show(),
            CoreMatchers.equalTo(
                String.format("sentence:String:%s", sentence)
            )
        );
    }
}
