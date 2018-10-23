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
package uy.kerri.representations;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;

/**
 * Tests for {@link uy.kerri.representations.FixedOutput}.
 *
 * @since 2.0
 */
public class FixedOutputTest {
    /**
     * FixedOutput shows an empty string if empty.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsEmptyStringIfEmpty() throws Exception {
        MatcherAssert.assertThat(
            "Empty output isn't empty.",
            new FixedOutput().show(),
            CoreMatchers.equalTo("")
        );
    }

    /**
     * FixedOutput shows its object if not empty.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void showsObject() throws Exception {
        final Integer value = 25;
        MatcherAssert.assertThat(
            "FixedOutput isn't showing its value.",
            new FixedOutput(value).show(),
            CoreMatchers.equalTo(value.toString())
        );
    }

    /**
     * FixedOutput is not mutated if printed.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public final void isNotMutated() throws Exception {
        final Double value = 2.7;
        MatcherAssert.assertThat(
            "FixedOutput is mutated when printed.",
            new FixedOutput(value).print("whatever", "something")
            .print("filler", 0).print("dummy", true).print("irrelevant", 0.0)
            .print("placeholder", 1L).print(
                "ignored",
                new ArrayOfFields(new LabelledString("label", "string"))
            ).print(
                "doesntmatter",
                new ArrayOfValues(
                    new LabelledString("anotherlabel", "anotherstring")
                )
            ).show(),
            CoreMatchers.equalTo(value.toString())
        );
    }
}
