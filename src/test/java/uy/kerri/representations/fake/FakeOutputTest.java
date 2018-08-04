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

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.fake.FakeOutput;

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
    public final void printsStringField() throws Exception {
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
}
